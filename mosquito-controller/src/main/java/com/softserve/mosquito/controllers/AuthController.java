package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserDto;
import com.softserve.mosquito.security.JwtAuthenticationResponse;
import com.softserve.mosquito.security.JwtTokenProvider;
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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider tokenProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public AuthController() {

    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto loginRequest) {
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDto signUpRequest) {
        if (userService.getByEmail(signUpRequest.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        // Creating user's account
        signUpRequest.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        /*UserDto result = userService.save(signUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(result.getId()).toUri();*/
        userService.save(signUpRequest);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/activate/{key}")
    public ResponseEntity activateAccount(@PathVariable("key") String key) {

        userService.activateUser(key);

        return ResponseEntity.ok().build();
    }
}
