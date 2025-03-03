package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.Farmers;
import Kisan.Sathi.Repositries.FarmersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FarmersModel {
    @Autowired
    FarmersRepo farmersRepo;

    public void createAccount(Farmers farmers){

        farmersRepo.save(farmers);
    }
}
