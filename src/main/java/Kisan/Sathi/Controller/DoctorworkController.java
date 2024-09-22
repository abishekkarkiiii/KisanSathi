package Kisan.Sathi.Controller;


import Kisan.Sathi.Entity.ProfileBus;
import Kisan.Sathi.Model.DoctorWork;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctorwork")
public class DoctorworkController {
    @Autowired
    DoctorWork doctorWork;
    @PostMapping("/contactDoctor")
    public boolean contactDoctor(@RequestBody ProfileBus farmer) throws MessagingException {
       return doctorWork.CheckDoctorApproval(farmer);

    }

    @PostMapping("/doctorPendingWork")
    public List<String> checkWork(ProfileBus profileBus){
        System.out.println("helloDoctor profile controller");
        System.out.println(profileBus);
        return doctorWork.workView(profileBus) ;

    }

    @PostMapping("/contactFarmer")
    public boolean Contactfarmer(ProfileBus profileBus){
        return  doctorWork.ContactFarmercheck(profileBus);
    }


}
