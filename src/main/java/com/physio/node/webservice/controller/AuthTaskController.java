package com.physio.node.webservice.controller;

import com.physio.node.webservice.jwt.JwtTokenProvider;
import com.physio.node.webservice.model.DTO.Auth.PasswordResetModel;
import com.physio.node.webservice.model.DTO.User.UserReadModel;
import com.physio.node.webservice.model.DTO.User.UserWriteModel;
import com.physio.node.webservice.service.UserService;
import com.physio.node.webservice.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthTaskController {
    private UserService userService;
    private AuthService authService;

    AuthTaskController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }



    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        return  authService.login(principal);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserWriteModel user){
        return authService.registerUser(user);
    }


/*
    Reset Password impl
 */
    @GetMapping("/resetTokenValidation/{token}")
    boolean isResetTokenValid(@PathVariable String token){
        try{
            boolean valid = userService.findUserByUserPasswordResetToken(token);
            return valid;
        }catch (NoSuchElementException e){
            return false;
        }

    }
    @GetMapping("/genereateResetToken/{email}")
    ResponseEntity<?> generateResetToken(@PathVariable String email){
        return authService.executePaswordReset(email);
    }
    @PostMapping("/changePasswordWithResetToken")
    ResponseEntity<?> changePasswordByResetToken(@RequestBody PasswordResetModel passwordResetModel){
        return authService.changePassword(passwordResetModel);
    }




}
