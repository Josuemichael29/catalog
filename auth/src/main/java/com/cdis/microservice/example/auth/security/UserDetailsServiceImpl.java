package com.cdis.microservice.example.auth.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cdis.microservice.example.auth.repository.UserRepository;
import com.cdis.microservice.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // hard coding the users. All passwords must be encoded.
        final List<com.cdis.microservice.example.auth.model.User> users = userRepository.findAll();

        List<com.cdis.microservice.example.auth.model.User> userList = new ArrayList<>();

        for(com.cdis.microservice.example.auth.model.User user : users) {
            com.cdis.microservice.example.auth.model.User userToBeAdded = user;
            userToBeAdded.setPassword(encoder.encode(user.getPassword()));
            userList.add(user);
        }

        System.out.println(userList.get(1).getPassword());

        for(com.cdis.microservice.example.auth.model.User user : userList) {
            if(user.getUsername().equals(username)) {

                // Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
                // So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_" + user.getRole().getName());

                // The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
                // And used by auth manager to verify and check user authentication.
                return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
            }
        }

        // If user not found. Throw this exception.
        System.out.println("User not found");
        throw new UsernameNotFoundException("Username: " + username + " not found");
    }
}
