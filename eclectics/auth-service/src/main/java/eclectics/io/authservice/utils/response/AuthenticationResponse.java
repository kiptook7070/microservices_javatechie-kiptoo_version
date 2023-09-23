package eclectics.io.authservice.utils.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class AuthenticationResponse {
    private String token;
}
