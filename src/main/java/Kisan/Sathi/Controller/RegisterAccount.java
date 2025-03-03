package Kisan.Sathi.Controller;

import Kisan.Sathi.Entity.Doctorsrequest;
import Kisan.Sathi.Entity.Users;
import Kisan.Sathi.Model.AccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/CreateAccount")
public class RegisterAccount {

    @Autowired
    AccountModel accountModel;
    @PostMapping
    public boolean CreateAccount(@RequestBody Users farmersData){
        farmersData.setPassword(new BCryptPasswordEncoder().encode(farmersData.getPassword()));
       return accountModel.createAccount(farmersData);

    }

   @PostMapping("/Register")
   public ResponseEntity<String> CreateDoctorAccount(@RequestParam("pdfFile") MultipartFile file,
                                                     @RequestParam("username") String username,
                                                     @RequestParam("password") String password,
                                                     @RequestParam("NationalIDNumber") String nationalID,
                                                     @RequestParam("Name") String Name,
                                                     @RequestParam("Qualification") String Qualification,
                                                     @RequestParam("Number") String PhoneNumber
                                                     ){
       Doctorsrequest doctorsRequestList =new Doctorsrequest();
       doctorsRequestList.setUsername(username);
       doctorsRequestList.setPassword(password);
       doctorsRequestList.setNationalIdNumber(nationalID);
       doctorsRequestList.setQualification(Qualification);
       doctorsRequestList.setName(Name);
       doctorsRequestList.setPhoneNumber(PhoneNumber);
       System.out.println(doctorsRequestList);
       return  accountModel.CreateDoctorAccount(file, doctorsRequestList);

   }

}
