package Kisan.Sathi.Controller;

import Kisan.Sathi.Entity.Doctor;
import Kisan.Sathi.Entity.Doctorsrequest;
import Kisan.Sathi.Model.RequestModel;
import Kisan.Sathi.Repositries.DoctorRepo;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@NoArgsConstructor
@RestController
@RequestMapping("/request")
public class Request {
    @Autowired
    RequestModel requestModel;
    @Autowired
    DoctorRepo doctorRepo;
    @GetMapping
    public List<Doctorsrequest> lists(){
        System.out.println("request");
        return requestModel.requestlist();
    }

    @GetMapping("/doctors")
    public List<Doctor>getALlDoctor(){
        return doctorRepo.findAll();
    }


}
