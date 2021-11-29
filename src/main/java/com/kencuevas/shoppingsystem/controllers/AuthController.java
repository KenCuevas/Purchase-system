package com.kencuevas.shoppingsystem.controllers;

import com.kencuevas.shoppingsystem.dto.JWTAuthResponse;
import com.kencuevas.shoppingsystem.dto.LoginDTO;
import com.kencuevas.shoppingsystem.dto.SignUpDTO;
import com.kencuevas.shoppingsystem.models.Role;
import com.kencuevas.shoppingsystem.models.User;
import com.kencuevas.shoppingsystem.repositories.RoleRepository;
import com.kencuevas.shoppingsystem.repositories.UserRepository;
import com.kencuevas.shoppingsystem.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@Api(value = "Auth controller exposes signin and signup REST APIs")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation(value = "REST API to register or signup user to purchase system")
    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse>authenticateUser(@RequestBody LoginDTO loginDTO){
       Authentication authentication = authenticate.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // get token from tokenProvider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
    @ApiOperation(value = "REST API to signin or login user to purchase system")
    @PostMapping("/signup")
    public ResponseEntity<?>registerUser(@RequestBody SignUpDTO sign){
        //Add check for username exists in a DB
        if(userRepository.existsByUsername(sign.getUsername())){
            return new ResponseEntity<>("Username is already taken!!", HttpStatus.BAD_REQUEST);
        }
        // Add check for email exist in DB
        if(userRepository.existsByEmail(sign.getEmail())){
            return new ResponseEntity<>("Email is already taken!!", HttpStatus.BAD_REQUEST);
        }
        // Create user object
        User user = new User();
        user.setName(sign.getName());
        user.setUsername(sign.getUsername());
        user.setEmail(sign.getEmail());
        user.setPassword(passwordEncoder.encode(sign.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User register successfully", HttpStatus.OK);
    }
}
