package Kisan.Sathi.Controller;

import Kisan.Sathi.Entity.DataBus;
import Kisan.Sathi.Model.RequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class Admin {
@Autowired
    RequestModel requestModel;
    @PostMapping("/approveDoctor")
    public ResponseEntity<String> CheckDoctor(@RequestBody DataBus dataBus){
           System.out.println( dataBus);
           requestModel.ApproveDoctor(dataBus);
          return  ResponseEntity.ok("file uploaded sucessfully");
    }

}
