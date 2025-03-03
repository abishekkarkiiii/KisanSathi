package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.Doctor;
import Kisan.Sathi.Entity.Doctorsrequest;
import Kisan.Sathi.Entity.Users;
import Kisan.Sathi.Repositries.DoctorRepo;
import Kisan.Sathi.Repositries.DoctorRequestList;
import Kisan.Sathi.Repositries.UsersAccount;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;

@Component

public class AccountModel {

    @Autowired
    UsersAccount usersAccount;

    @Autowired
    DoctorRequestList doctorRequestListInterface;
    @Autowired
    DoctorRepo doctorRepo;
    public boolean createAccount(Users farmers){
        try {
           usersAccount.save(farmers);
           return true;
        }catch(Exception e){
            return false;
        }
    }

    public Users findUsers(String username){
          return usersAccount.findByusername(username);
    }
    public Doctor findDoctor(String email){
        return doctorRepo.findByemail(email);}

    @Autowired
    JavaMailSender mailSender;
    public ResponseEntity<String> CreateDoctorAccount(MultipartFile file, Doctorsrequest doctorsRequestList){

        System.out.println(file.getName());
        try {
            InputStream inputStream=file.getInputStream();
            byte[] data =new byte[inputStream.available()];
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            inputStream.read(data);
            helper.setFrom("abishekkarki200614@gmail.com");
            helper.setTo("ak2017926@gmail.com");
            helper.setSubject("Doctor"+doctorsRequestList.getName()+" applied please verify from server");
            helper.setText("email: " + doctorsRequestList.getUsername());
            helper.addAttachment("DoctorRepo PDF", new ByteArrayDataSource(data, "application/pdf"));
            FileOutputStream writer=new FileOutputStream("E:\\KisanSathi\\src\\main\\resources\\Approval"+"\\"+ doctorsRequestList.getUsername()+".pdf");
            writer.write(data);
            AccountSave(doctorsRequestList);
            mailSender.send(mimeMessage);
        }catch(Exception e){
          e.printStackTrace();
        }
        return  ResponseEntity.ok("file uploaded sucessfully");

    }

 private void AccountSave(Doctorsrequest doctorsRequestList){
        Doctorsrequest isPresent=doctorRequestListInterface.findByusername(doctorsRequestList.getUsername());
        if(isPresent==null){
            this.doctorRequestListInterface.save(doctorsRequestList);
           doctorsRequestList.setDoctorId(doctorRequestListInterface.findByusername(doctorsRequestList.getUsername()).getObjectId().toString());
            System.out.println( this.doctorRequestListInterface.save(doctorsRequestList).getDoctorId());
        }else {
            System.out.println("user already exist");
        }

 }




}
