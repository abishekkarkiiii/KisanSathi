package Kisan.Sathi.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Component
@Document(collection = "farmers")
public class Farmers {
    private String phonenumber;
    private String address;
    private String username;
    private String citizenshipNumber;
    private String panNumber;
    private String panID;
    private String role="Farmer";
}
