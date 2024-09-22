package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.DataBus;
import Kisan.Sathi.Entity.Doctor;
import Kisan.Sathi.Entity.Doctorsrequest;
import Kisan.Sathi.Repositries.DoctorRepo;
import Kisan.Sathi.Repositries.DoctorRequestList;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestModel {

    @Autowired
    private DoctorRequestList doctorsRequestList;
    @Autowired
    DoctorRepo doctorRepositries;

    @Autowired
    DoctorRequestList doctorRequestListRepo;


    public List<Doctorsrequest> requestlist() {
        return doctorsRequestList.findAll();
    }

    public boolean ApproveDoctor(DataBus dataBus){
        if(dataBus.isApproved()){
           Doctorsrequest doctorRequest= doctorsRequestList.findByobjectId(new ObjectId(dataBus.getDoctorId()));
            Doctor doctor=new Doctor();
            doctor.setName(doctorRequest.getName());
            doctor.setEmail(doctorRequest.getUsername());
            doctor.setPassword(new BCryptPasswordEncoder().encode(doctorRequest.getPassword()));
            doctor.setNationalIdNumber(doctorRequest.getNationalIdNumber());
            doctor.setQualification(doctorRequest.getQualification());
            doctor.setDoctorId(doctorRequest.getDoctorId());
            doctor.setPhonenumber(doctorRequest.getPhoneNumber());
            System.out.println(doctor.getEmail());
            doctorRepositries.save(doctor);
            doctorRequestListRepo.delete(doctorRequest);


            return  true;
        }else {
            Doctorsrequest doctorRequest= doctorsRequestList.findByobjectId(new ObjectId(dataBus.getDoctorId()));
            doctorRequestListRepo.delete(doctorRequest);
            return  false;
        }

    }
}
