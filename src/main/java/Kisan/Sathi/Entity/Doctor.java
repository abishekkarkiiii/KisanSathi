package Kisan.Sathi.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Approved Doctors")
public class Doctor {
    @NonNull
    @Indexed(unique = true)
    private String email;//Email
    private  String password;
    private String NationalIdNumber;
    private String Name;
    private String Qualification;
    private String Role="Doctor";
    private String doctorId;
    private String Phonenumber;
    private String picture;
    private ObjectId id;
    @Indexed(unique = true)
    private List<String> serviceWaiting=new ArrayList<>();
    private
    @DBRef
    List<Services>services=new ArrayList<>();
    private double PendingFees;
    private String profileImage;

}
