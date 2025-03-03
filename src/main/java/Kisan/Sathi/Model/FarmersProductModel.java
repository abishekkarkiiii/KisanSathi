package Kisan.Sathi.Model;

import Kisan.Sathi.Entity.FarmersProduct;
import Kisan.Sathi.Repositries.FarmersProductsDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FarmersProductModel {
    @Autowired
    FarmersProductsDB farmersProduct;

    @Autowired
    FileOperationsForProject fileOperationsForProject;

    @Autowired
     ProfileModel profileModel;

    public void productRegister(FarmersProduct product, MultipartFile file){
        product.setImagePath(fileOperationsForProject.saveImageInServer(file,product.getUploadedContact(),"E:\\KisanSathi\\FarmersProduct"));
        System.out.println("debugging"+product);
        farmersProduct.save(product);
    }

    public List<FarmersProduct> getAllProduct(){
        List <FarmersProduct> farmerProduct=farmersProduct.findAll();
        farmerProduct=farmerProduct.stream().map(x->{
               x.setCitizenshipNumber("");
               return x;
        }).collect(Collectors.toList());

       return farmerProduct;
    }









}
