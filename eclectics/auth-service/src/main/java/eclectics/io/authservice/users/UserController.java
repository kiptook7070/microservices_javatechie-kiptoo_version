package eclectics.io.authservice.users;

import eclectics.io.authservice.users.requests.LoginRequest;
import eclectics.io.authservice.users.requests.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest register) {
        try {
            return ResponseEntity.ok(userService.register(register));
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok().body(userService.login(request));
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> users() {
        try {
            return ResponseEntity.ok().body(userService.users());
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }
    }
}
