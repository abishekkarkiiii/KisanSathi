package Kisan.Sathi.Entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Profile {
    private  String name;
    private String email;
    private String phoneNumber;
    private String qualification;
    private String picture;

}
