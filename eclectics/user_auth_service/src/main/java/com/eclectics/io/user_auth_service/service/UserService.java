package com.eclectics.io.user_auth_service.service;


import com.eclectics.io.user_auth_service.entities.User;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * <h2>UserService</h2>
 *
 * @author aek
 * <p>
 * Description:
 */
public interface UserService extends UserDetailsService, UserDetailsPasswordService {

    /**
     * @return list of User
     */
    List<User> getUsers();

    /**
     * @param user ussr object
     * @return user saved or updated
     */
    User save(User user);
}
