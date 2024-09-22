package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.*;
import Kisan.Sathi.Repositries.DoctorRepo;
import Kisan.Sathi.Repositries.UsersAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.InputStream;


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
        Farmers Farmer= usersAccount.findByusername(profileBus.getEmail());
        return Profile.builder().name(Farmer.getUsername()).phoneNumber(Farmer.getPhoneNumber()).name(Farmer.getName()).build();
    }

    public void profileupdate(ProfileDataUpdate profileDataUpdate){
        System.out.println(profileDataUpdate+"profileUpdate");
        Doctor doctor= doctorRepo.findByemail(profileDataUpdate.getEmail());
            try {
                InputStream inputStream = profileDataUpdate.getFile().getInputStream();
                byte[] data = inputStream.readAllBytes();
                FileOutputStream writer = new FileOutputStream("D:\\Sathi\\src\\main\\resources\\static\\ProfilePics" + "\\" + doctor.getNationalIdNumber() + ".jpg");
                doctor.setPicture(doctor.getNationalIdNumber());
                doctorRepo.deleteById(doctor.getId());
                doctorRepo.save(doctor);
                writer.write(data);
            } catch (Exception e) {
                Farmers user=usersAccount.findByusername(profileDataUpdate.getEmail());
                System.out.println(user.getUsername());

            }

        System.out.println(doctor.getDoctorId());
    }

    public String DecisionMake(ProfileBus profileBus){
        Farmers user=usersAccount.findByusername(profileBus.getEmail());
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

}
