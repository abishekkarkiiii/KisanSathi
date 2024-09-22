package Kisan.Sathi.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection = "Farmers")
@Component
@NoArgsConstructor
public class Farmers {
    @NonNull
    @Indexed(unique = true)
    private  String username;
    @NonNull
    private String password;
    private String Role="Farmer";
    private String image;
    private String phoneNumber;
    private String name;

}
