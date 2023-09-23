package eclectics.io.authservice.users;

import eclectics.io.authservice.roles.Roles;
import eclectics.io.authservice.users.requests.LoginRequest;
import eclectics.io.authservice.users.requests.RegisterRequest;
import eclectics.io.authservice.utils.config.JwtService;
import eclectics.io.authservice.utils.config.JwtUtil;
import eclectics.io.authservice.utils.response.EntityResponse;
import eclectics.io.authservice.utils.response.JwtResponse;
import eclectics.io.authservice.utils.validations.PasswordGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public EntityResponse register(RegisterRequest request) {
        try {
            EntityResponse response = new EntityResponse();
            PasswordGeneratorUtil passwordGeneratorUtil = new PasswordGeneratorUtil();
            String generatedPassword = passwordGeneratorUtil.generatePassayPassword();
            // Create new user's account
            request.setPassword(generatedPassword);

            PasswordData passwords = new PasswordData(request.getPassword());

//        TODO: Password Should not contain username
            Rule rule = new UsernameRule();
            PasswordValidator usernamevalidator = new PasswordValidator(rule);
            passwords.setUsername(request.getUsername());
            RuleResult results = usernamevalidator.validate(passwords);

            if (results.isValid()) {
                System.out.println("FINAL " + results.isValid());
//            TODO: Username is unique
                Optional<User> checkUser = userRepository.findUserByUsername(request.getUsername());
                System.out.println("User " + checkUser);
                if (checkUser.isPresent()) {
                    response.setMessage("username " + checkUser.get().getUsername() + " Already Taken: !!");
                    response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
                    return response;
                } else {
//                TODO: Email is unique
                    Optional<User> checkEmail = userRepository.findUserByEmail(request.getEmail());
                    if (checkEmail.isPresent()) {
                        response.setMessage("Email Address " + checkUser.get().getEmail() + " Already Taken: !!");
                        response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
                        return response;
                    } else {
//                    TODO: Phone number is unique
                        Optional<User> checkPhone = userRepository.findUserByMobile(request.getMobile());
                        if (checkPhone.isPresent()) {
                            response.setMessage("The Phone number " + checkUser.get().getMobile() + " Already Taken: !!");
                            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
                            return response;
                        } else {
                            User user = new User();
                            user.setPostedTime(new Date());
                            user.setPostedFlag('Y');
                            user.setPostedBy("SYSTEM");
                            user.setIsAcctLocked(false);
                            user.setFirstname(request.getFirstname());
                            user.setLastname(request.getLastname());
                            user.setMobile(request.getMobile());
                            user.setEmail(request.getEmail());
                            user.setUsername(request.getUsername());
                            user.setRoles(Roles.ADMIN);
                            user.setPassword(passwordEncoder.encode(request.getPassword()));
                            User createUser = userRepository.save(user);
                            response.setMessage("User " + createUser.getUsername() + " has been registered successfully on " + new Date() + " Your Password is : " + request.getPassword());
                            response.setStatusCode(HttpStatus.CREATED.value());
                            response.setEntity(createUser);
                        }

                    }
                }

            } else {
                response.setMessage("Password should not contain the username provided i.e " + request.getUsername());
                response.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
            }
            return response;
        } catch (Exception e) {
            log.info("Caught Error" + e);
            return null;
        }
    }

    public EntityResponse login(LoginRequest request) {
        try {
            EntityResponse response = new EntityResponse();
            Optional<User> checkUser = userRepository.findUserByUsername(request.getUsername());
            if (checkUser.isPresent()) {
                User user = checkUser.get();
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtil.generateJwtToken(authentication);
                System.out.println("Context Authenication" + authentication);
                System.out.println("JWT " + jwt);
                JwtResponse jwtResponse = new JwtResponse();
                jwtResponse.setId(Long.valueOf(user.getId().toString()));
                jwtResponse.setToken(jwt);
                jwtResponse.setEmail(user.getEmail());
                jwtResponse.setUsername(user.getUsername());
                jwtResponse.setFirstName(user.getFirstname());
                jwtResponse.setLastName(user.getLastname());
                jwtResponse.setRoles(Collections.singleton(user.getRoles()));
                jwtResponse.setMobile(user.getMobile());
                response.setMessage(HttpStatus.OK.getReasonPhrase());
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(jwtResponse);
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return response;
        } catch (Exception e) {
            log.info("Caught Error" + e);
            return null;
        }

    }


    public EntityResponse users() {
        try {
            EntityResponse response = new EntityResponse();
            List<User> users = userRepository.findAll();
            if (users.size() > 0) {
                response.setMessage(HttpStatus.FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.FOUND.value());
                response.setEntity(users);
            } else {
                response.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }
            return response;
        } catch (Exception e) {
            log.info("Caught Error " + e);
            return null;
        }

    }
}
