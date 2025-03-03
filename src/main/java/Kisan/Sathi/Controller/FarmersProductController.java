package Kisan.Sathi.Controller;

import Kisan.Sathi.Model.FarmersProductModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import Kisan.Sathi.Entity.FarmersProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RequestMapping("/FarmersProduct")
@RestController
public class FarmersProductController {
    @Autowired
    FarmersProductModel farmersModel;

    @PostMapping("/registerProduct")
    public ResponseEntity<String> registerProduct(
            @RequestParam("farmersProduct") String farmersProductJson, // Receive the JSON string
            @RequestParam("productImage") MultipartFile productImage) throws Exception {
        System.out.println(farmersProductJson);

        // Deserialize the JSON string to a FarmersProduct object
        ObjectMapper objectMapper = new ObjectMapper();
        FarmersProduct farmersProduct = objectMapper.readValue(farmersProductJson, FarmersProduct.class);

        // Print the received data for debugging
        System.out.println(farmersProduct);


        System.out.println("Product Image: " + productImage.getOriginalFilename());
        farmersModel.productRegister(farmersProduct,productImage);
        // Handle saving the product and file (e.g., save the product in the database, store image)

        // Respond with a success message
        return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Product registered successfully!\"}");
    }


    @GetMapping("/getProduct")
    public List<FarmersProduct> getAllProducts(){
        return farmersModel.getAllProduct();
    }





}
