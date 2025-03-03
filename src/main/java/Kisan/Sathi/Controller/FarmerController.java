package Kisan.Sathi.Controller;

import Kisan.Sathi.Entity.Farmers;
import Kisan.Sathi.Model.FarmersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/Farmers")
public class FarmerController {

    @Autowired
    FarmersModel farmersModel;
    @PostMapping
    public ResponseEntity<String> registerProfile(@RequestPart("file")MultipartFile file, @RequestPart("farmers") Farmers farmers) {
        farmersModel.createAccount(farmers);
        return ResponseEntity.ok("Farmers Account is created");
    }

}
