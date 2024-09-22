package Kisan.Sathi.Controller;

import Kisan.Sathi.Entity.DataBus;
import Kisan.Sathi.Entity.Profile;
import Kisan.Sathi.Entity.ProfileBus;
import Kisan.Sathi.Entity.ProfileDataUpdate;
import Kisan.Sathi.Model.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    ProfileModel profileModel;
    @PostMapping("/doctor-profile")
    public Profile viewProfile(@RequestBody DataBus data){
         return profileModel.findprofile(data);
    }

    @PostMapping("/data")
    public ResponseEntity<String>  CheckDoctor(@RequestBody ProfileBus profileBus){
         profileModel.DoctorProfileData(profileBus);
         return  ResponseEntity.ok("sucessfull");
    }

    @PostMapping("/profile-data")
    public  Profile updateProfile(@RequestBody ProfileBus profileBus){
        System.out.println(profileBus+"adsadasdsadsadasdasda");
        if(profileModel.DecisionMake(profileBus).equals("Doctor")){
            return  profileModel.DoctorProfileData(profileBus);
        }else {
//            if(profileModel.DecisionMake(profileBus).equals("Farmer"))
            return  profileModel.DoctorProfileData(profileBus);
        }

    }

    @PostMapping("/profile-update")
    public ResponseEntity<?> profileUpdate(
            @RequestParam("about-me") String aboutMe,
            @RequestParam("email") String email,
            @RequestPart(value = "profile-pic", required = false) MultipartFile profilePic) {

        ProfileDataUpdate profileDataUpdate=ProfileDataUpdate.builder()
                .about(aboutMe)
                .file(profilePic)
                .email(email).build();
        profileModel.profileupdate(profileDataUpdate);
        return ResponseEntity.ok("Profile updated successfully");
    }




}
