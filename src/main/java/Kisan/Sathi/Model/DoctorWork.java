package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.Doctor;
import Kisan.Sathi.Entity.Profile;
import Kisan.Sathi.Entity.ProfileBus;
import Kisan.Sathi.Repositries.DoctorRepo;
import Kisan.Sathi.Repositries.UsersAccount;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorWork {
    @Autowired
    ProfileModel profileModel;
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UsersAccount farmersAccount;

    // Method to handle farmer reaching out to doctor
    private void DoctorWorkgiver(ProfileBus profileBus) throws MessagingException {
        Profile farmer = profileModel.Farmerprofile(profileBus);
        Doctor doctor = doctorRepo.findByDoctorId(profileBus.getDoctorId());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("abishekkarki200614@gmail.com");
        mimeMessageHelper.setTo(doctor.getEmail());
        mimeMessageHelper.setSubject("Urgent: Farmer Request for Assistance");

        String messageBody = "Dear Dr. " + doctor.getName() + ",\n\n" +
                getGreeting() + ".\n\n" +
                "We hope this message finds you well. A farmer is trying to reach out for your assistance. Please find the farmer's details below:\n\n" +
                "Farmer Name: " + farmer.getName() + "\n" +
                "Phone Number: " + farmer.getPhoneNumber() + "\n\n" +
                "We kindly request you to get in touch with the farmer at your earliest convenience.\n\n" +
                "Thank you for using KisanSathi's platform.\n\n" +
                "Best regards,\n" +
                "The KisanSathi Team";

        mimeMessageHelper.setText(messageBody);
        javaMailSender.send(mimeMessage);
    }

    // Get greeting based on current time of day
    public static String getGreeting() {
        LocalTime currentTime = LocalTime.now();
        LocalTime morningStart = LocalTime.of(5, 0);
        LocalTime afternoonStart = LocalTime.of(12, 0);
        LocalTime eveningStart = LocalTime.of(17, 0);
        LocalTime nightStart = LocalTime.of(21, 0);

        if (currentTime.isAfter(morningStart) && currentTime.isBefore(afternoonStart)) {
            return "Good Morning";
        } else if (currentTime.isAfter(afternoonStart) && currentTime.isBefore(eveningStart)) {
            return "Good Afternoon";
        } else if (currentTime.isAfter(eveningStart) && currentTime.isBefore(nightStart)) {
            return "Good Evening";
        } else {
            return "Good Night";
        }
    }

    // Check for doctor approval and notify
    public boolean CheckDoctorApproval(ProfileBus profileBus) {
        Doctor doctor = doctorRepo.findByDoctorId(profileBus.getDoctorId());
        if (!doctor.getServiceWaiting().contains(profileBus.getEmail())) {
            doctor.getServiceWaiting().add(profileBus.getEmail());
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                mimeMessageHelper.setFrom("abishekkarki200614@gmail.com");
                mimeMessageHelper.setTo(doctor.getEmail());
                mimeMessageHelper.setSubject("Action Required: New Farmer Request");

                String messageBody = "Dear Dr. " + doctor.getName() + ",\n\n" +
                        getGreeting() + ".\n\n" +
                        "A farmer has requested your assistance. Please check your dashboard for details.\n\n" +
                        "Thank you for your continued support on KisanSathi.\n\n" +
                        "Best regards,\n" +
                        "The KisanSathi Team";

                mimeMessageHelper.setText(messageBody);
                javaMailSender.send(mimeMessage);

                doctorRepo.save(doctor);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            System.out.println("Sorry, request already exists.");
            return false;
        }
    }

    // View the pending work for a doctor
    public List<String> workView(ProfileBus profile) {
        List<String> list = new ArrayList<>();
        doctorRepo.findByemail(profile.getEmail()).getServiceWaiting().forEach((x -> {
            list.add(x + ",," + farmersAccount.findByusername(x).getName());
        }));
        return list;
    }

    // Contact farmer, checking for doctor approval and payment status
    public boolean ContactFarmercheck(ProfileBus profileBus) {
        System.out.println(profileBus);
        if (profileBus.isDoctorApproval()) {
            profileBus.setDoctorId(doctorRepo.findByemail(profileBus.getEmail()).getDoctorId());
            final String temp = profileBus.getEmail();
            profileBus.setEmail(profileBus.getId());
            try {
                System.out.println(profileBus.getEmail() + " - email going out");
                Doctor doctor = doctorRepo.findByemail(temp);

                // Check for pending fees
                if (doctor.getPendingFees() >= 2000) {
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                    mimeMessageHelper.setFrom("abishekkarki200614@gmail.com");
                    mimeMessageHelper.setTo(doctor.getEmail());
                    mimeMessageHelper.setSubject("Payment Required: Pending Balance");

                    String messageBody = "Dear Dr. " + doctor.getName() + ",\n\n" +
                            getGreeting() + ".\n\n" +
                            "We regret to inform you that due to your outstanding balance of Rs. " + doctor.getPendingFees() +
                            ", we are unable to provide farmer contact details until the balance is cleared.\n\n" +
                            "Please settle the pending amount at your earliest convenience to continue using our services.\n\n" +
                            "Thank you for your understanding and prompt attention to this matter.\n\n" +
                            "Best regards,\n" +
                            "The KisanSathi Team";

                    mimeMessageHelper.setText(messageBody);
                    javaMailSender.send(mimeMessage);

                } else if (doctor.getPendingFees() >= 500) {
                    doctor.setPendingFees(pendingFeesCalculator(doctor));
                    DoctorWorkgiver(profileBus);

                    List<String> list = doctor.getServiceWaiting();
                    if (list.remove(profileBus.getId())) {
                        doctor.setServiceWaiting(list);
                        doctorRepo.save(doctor);

                        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                        mimeMessageHelper.setFrom("abishekkarki200614@gmail.com");
                        mimeMessageHelper.setTo(doctor.getEmail());
                        mimeMessageHelper.setSubject("Reminder: Pending Payment Due");

                        String messageBody = "Dear Dr. " + doctor.getName() + ",\n\n" +
                                getGreeting() + ".\n\n" +
                                "This is a reminder to settle your pending balance of Rs. " + doctor.getPendingFees() +
                                ". Please ensure timely payment to avoid service interruptions.\n\n" +
                                "Thank you for your cooperation.\n\n" +
                                "Best regards,\n" +
                                "The KisanSathi Team";

                        mimeMessageHelper.setText(messageBody);
                        javaMailSender.send(mimeMessage);

                        System.out.println("Deleted request successfully after sending email");
                    } else {
                        System.out.println("Failed to delete the data");
                    }
                }else{
                    doctor.setPendingFees(pendingFeesCalculator(doctor));
                    DoctorWorkgiver(profileBus);

                    List<String> list = doctor.getServiceWaiting();
                    if (list.remove(profileBus.getId())) {
                        doctor.setServiceWaiting(list);
                        doctorRepo.save(doctor);
                        System.out.println("mail send successfully and deleted");
                    }else{
                        System.out.println("mail could not send");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            Doctor doctor = doctorRepo.findByemail(profileBus.getEmail());
            List<String> list = doctor.getServiceWaiting();
            if (list.remove(profileBus.getId())) {
                doctor.setServiceWaiting(list);
                doctorRepo.save(doctor);
                System.out.println("Deleted request successfully");
            } else {
                System.out.println("Failed to delete the data");
            }
            return false;
        }
    }


    private double pendingFeesCalculator(Doctor doctor) {
        return doctor.getPendingFees() + 100;
    }
}
