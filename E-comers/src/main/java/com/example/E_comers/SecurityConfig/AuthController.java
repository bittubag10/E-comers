package com.example.E_comers.SecurityConfig;

import com.example.E_comers.DTO.Request.LoginRequest;
import com.example.E_comers.DTO.Request.SignupRequest;
import com.example.E_comers.DTO.Response.LoginResponse;
import com.example.E_comers.DTO.Response.SignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse>login(@RequestBody LoginRequest loginRequest){
        LoginResponse response=authService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse>signup(@RequestBody SignupRequest signupRequest){
        SignupResponse response=authService.signup(signupRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
