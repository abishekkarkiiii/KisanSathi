package Kisan.Sathi.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@Document(collection = "Users")
@Component
@NoArgsConstructor
public class Users {
    @NonNull
    @Indexed(unique = true)
    private  String username;
    @NonNull
    private String password;
    private String Role="User";
    private String image;
    private String phoneNumber;
    private String name;

}
