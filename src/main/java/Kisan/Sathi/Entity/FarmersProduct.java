package Kisan.Sathi.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Data
@Document(collection = "FarmersProduct")
@NoArgsConstructor
public class FarmersProduct {
    private String productName;
    private double price;
    private String categories;
    private String uploadedContact;
    private String citizenshipNumber;
    private String userName;
    private String imagePath;
    private String productQuantity;
    private String unit;
    private  String minimumOrder;
}
