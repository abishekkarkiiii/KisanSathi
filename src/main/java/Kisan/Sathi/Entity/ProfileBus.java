package Kisan.Sathi.Entity;

import lombok.Builder;
import lombok.Data;

@Data
public class ProfileBus {
    private String email;
    private String doctorId;
    private boolean doctorApproval;
    private String id;
}
