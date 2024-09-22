package Kisan.Sathi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
@AllArgsConstructor
public class Doctorsrequest {
    @Id
    private ObjectId objectId;
    @Indexed(unique = true)
    private String username;//Email
    private  String password;
    private String NationalIdNumber;
    private String Name;
    private String Qualification;
    private String DoctorId;
    private String PhoneNumber;
}
