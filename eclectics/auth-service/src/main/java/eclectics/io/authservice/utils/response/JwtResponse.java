package eclectics.io.authservice.utils.response;

import eclectics.io.authservice.roles.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String mobile;
    private String email;
    private Set<Roles> roles;
    private String Status;
    private String loginAt;
    private String address;
    private String os;
    private String browser;
}
