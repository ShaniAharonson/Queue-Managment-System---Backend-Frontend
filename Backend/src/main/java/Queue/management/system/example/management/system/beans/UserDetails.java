package Queue.management.system.example.management.system.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDetails {
    private int userId;
    private String email;
    private String password;
    private String userName;
    private UserType userType;


}