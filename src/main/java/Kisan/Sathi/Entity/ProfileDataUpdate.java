package Kisan.Sathi.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ProfileDataUpdate {
    private String about;
    private String filename;
    private MultipartFile file;
    private String email;
}
