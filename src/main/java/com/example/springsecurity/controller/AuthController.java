package com.example.springsecurity.controller;

import com.example.springsecurity.dto.AuthRequest;
import com.example.springsecurity.dto.RegisterRequest;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repo.UserRepository;
import com.example.springsecurity.response.RegisterResponse;
import com.example.springsecurity.security.JwtTokenUtil;
import com.example.springsecurity.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws Exception {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username and Password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        User user = userRepository.findByUsername(authRequest.getUsername());
        // Check if the user is active
        if ("In-Active".equalsIgnoreCase(user.getActiveStatus())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message", "Admin Account is not active"));
        }
            String token = jwtTokenUtil.generateToken(userDetails);
            //  logger.info(token);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();

        // Check if username already exists
        if (userRepository.findByUsername(registerRequest.getUsername()) != null) {
            response.setMsg("Error: Username is already taken");
            response.setRequestId(null);
            return ResponseEntity.badRequest().body(response);
        }

        // Create a new user
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setEnabled(true);
        newUser.setRoles(registerRequest.getRoles());

        // Check if the user has an admin role
        boolean isAdmin = registerRequest.getRoles().contains("ROLE_ADMIN");
        newUser.setActiveStatus(isAdmin ? "In-Active" : "Active");

        // Save the new user to the database
        userRepository.save(newUser);

        // Create a requestId only for admin users
        if (isAdmin) {
            String requestId = UUID.randomUUID().toString();
            response.setMsg("User successfully registered. Activation pending for admin.");
            response.setRequestId(requestId);
        } else {
            response.setMsg("User registration successful.");
            response.setRequestId(null);
        }

        response.setUsername(registerRequest.getUsername());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/adminRegister")
    public ResponseEntity<?> adminRegister(@RequestBody RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.username) != null) {

            return ResponseEntity.badRequest().body("Already User Register");
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.username);
        newUser.setPassword(passwordEncoder.encode(registerRequest.password));
        newUser.setEnabled(true);
        //  newUser.setRoles(new HashSet<>(Collections.singletonList("ROLE_USER")));
        newUser.setRoles(registerRequest.getRoles());
        newUser.setActiveStatus("Active");
        userRepository.save(newUser);
        return ResponseEntity.ok("User Register Successfully");
    }
}
