package com.softserve.mosquito.security;


import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private UserService userService;

    @Autowired
    public CustomUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userEmail)
            throws UsernameNotFoundException {
        // Let people login with email
        UserDto user = userService.getByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email : " + userEmail);
        }
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        UserDto user = userService.getById(id);
        System.out.println("userLoadId:" + user + " User Email: " + user.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with id : " + id);
        }

        return UserPrincipal.create(user);
    }
}
