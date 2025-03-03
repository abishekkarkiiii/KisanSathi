package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.*;
import Kisan.Sathi.Repositries.DoctorRepo;
import Kisan.Sathi.Repositries.UsersAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class ProfileModel {
    @Autowired
    DoctorRepo doctorRepo;
    @Autowired
    UsersAccount usersAccount;
    public Profile findprofile(DataBus dataBus){
        System.out.println(dataBus);
        Doctor doctor=doctorRepo.findByDoctorId(dataBus.getDoctorId());
        Profile profile = Profile.builder()
                .name(doctor.getName())
                .email(doctor.getEmail())
                .phoneNumber(doctor.getPhonenumber())
                .qualification(doctor.getQualification())
                .picture(doctor.getPicture())
                .build();
        System.out.println(profile);
        return profile;
    }

    public Profile DoctorProfileData(ProfileBus profileBus){
        DataBus dataBus= new DataBus();
        dataBus.setDoctorId(doctorRepo.findByemail(profileBus.getEmail()).getDoctorId());
        return findprofile(dataBus);
    }
    public  Profile Farmerprofile(ProfileBus profileBus){
        System.out.println(profileBus+"emailsender request");
        Users Farmer= usersAccount.findByusername(profileBus.getEmail());
        return Profile.builder().name(Farmer.getUsername()).phoneNumber(Farmer.getPhoneNumber()).name(Farmer.getName()).build();
    }

    public void profileupdate(ProfileDataUpdate profileDataUpdate){
        System.out.println(profileDataUpdate+"profileUpdate");
        Doctor doctor= doctorRepo.findByemail(profileDataUpdate.getEmail());
            try {
                System.out.println("DoctorInformation:"+doctor);
                String previousImage="E:\\Projects\\KisanSathi\\ProfilePics" + "\\" +doctor.getPicture()+".jpg";
                InputStream inputStream = profileDataUpdate.getFile().getInputStream();
                byte[] data = inputStream.readAllBytes();

                String exactTime=getCurrentDateTime();
                FileOutputStream writer = new FileOutputStream("E:\\KisanSathi\\ProfilePics" + "\\" + doctor.getNationalIdNumber() + exactTime+".jpg");
                doctor.setPicture(doctor.getNationalIdNumber()+exactTime);
                doctorRepo.deleteById(doctor.getId());
                doctorRepo.save(doctor);
                writer.write(data);
                if(this.deleteFile(previousImage)){
                    System.out.println("updated to file also");
                }else{
                    System.out.println("sorry we cant update");
                };
            } catch (Exception e) {
                System.out.println();
                Users user=usersAccount.findByusername(profileDataUpdate.getEmail());
                System.out.println(user.getUsername());

            }

        System.out.println(doctor.getDoctorId());
    }

    public String DecisionMake(ProfileBus profileBus){
        Users user=usersAccount.findByusername(profileBus.getEmail());
        if(user!=null){
            return "Farmer";
        }else{
            Doctor doctor=doctorRepo.findByemail(profileBus.getEmail());
            if(doctor!=null){
                return "Doctor";
            }else{
                return "User Not found";
            }
        }

    }


    public static String getCurrentDateTime() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define the format to include date, hour, minute, second, and millisecond
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        // Return the formatted current date and time as a String
        return currentDateTime.format(formatter);
    }


    public boolean deleteFile(String path){
        File file= new File(path);
        System.out.println(file);
        return file.delete();
    }

 //For Practice purpose only neither use Response entity we can use Object as well
    public <T>  T ProfileChecker(ProfileBus profileBus){
        Doctor doctor=doctorRepo.findByemail(profileBus.getEmail());
        if(doctor!=null){
            //we can empty thge object but why to empty this data is going to their own so i only make two
            doctor.setDoctorId("");
            doctor.setPicture("");
            return (T)doctor;
        }
        Users farmers=usersAccount.findByusername(profileBus.getEmail());

        return (T) farmers;

    }




}
