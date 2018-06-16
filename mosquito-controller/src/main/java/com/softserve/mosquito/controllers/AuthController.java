package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.security.JwtAuthenticationResponse;
import com.softserve.mosquito.security.JwtTokenProvider;
import com.softserve.mosquito.security.LoginResponse;
import com.softserve.mosquito.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider,
                          PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid UserLoginDto loginRequest) {
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        }catch(AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDto signUpRequest) {
        if(userService.getByEmail(signUpRequest.getEmail()) != null) {
            return new ResponseEntity(new LoginResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Check if password and confirmPassword are same
        if(!signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword())){
            return new ResponseEntity(new LoginResponse(false, "Password and confirm password should be the same!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        UserDto result = userService.save(signUpRequest);

        UserDto returnedUser = UserDto.newBuilder().id(result.getId()).firstName(result.getFirstName()).lastName(result.getLastName()).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedUser);
    }
}
