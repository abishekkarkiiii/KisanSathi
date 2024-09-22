package Kisan.Sathi.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Data
@Component
public class Services {
    private String customerName;
    private String customerNationalId;
    private String treatmentDetails;
    private ObjectId id;
    private String serviceId;
    private double Fees;

}
