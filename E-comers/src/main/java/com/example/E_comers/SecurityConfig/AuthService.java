package com.example.E_comers.SecurityConfig;

import com.example.E_comers.DTO.Request.LoginRequest;
import com.example.E_comers.DTO.Request.SignupRequest;
import com.example.E_comers.DTO.Response.LoginResponse;
import com.example.E_comers.DTO.Response.SignupResponse;
import com.example.E_comers.ENUM.AuthProviderType;
import com.example.E_comers.ENUM.RoleType;
import com.example.E_comers.Model.Customer;
import com.example.E_comers.Model.User;
import com.example.E_comers.Repository.CustomerRepository;
import com.example.E_comers.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;

    public LoginResponse login(LoginRequest loginRequest) {

        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())

        );

        User user= (User) authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);

        return new LoginResponse(token, user.getId());
    }

    public User signUpInternal(SignupRequest signupRequest,AuthProviderType authProviderType,String providerId){
        User user=userRepository.findByUsername(signupRequest.getUsername()).orElse(null);

        if (user != null){
            throw new IllegalArgumentException("user already exist");
        }



        user=User.builder()
                .username(signupRequest.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .roles(signupRequest.getRoles())
                .build();



        // --- PROBLEM FIXED HERE ---
        if (authProviderType == AuthProviderType.EMAIL) {
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        } else {
            // Set a dummy password for OAuth2 users so the Database doesn't crash
            user.setPassword("");
        }
        // --------------------------
        user=userRepository.save(user);

        Customer customer= Customer.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getUsername())
                .user(user)
                .build();
        customerRepository.save(customer);
        return user;
    }


    //login controller
    public SignupResponse signup(SignupRequest signupRequest) {

        User user=signUpInternal(signupRequest,AuthProviderType.EMAIL,null);

        return new SignupResponse(user.getId(),user.getUsername());

    }

    @Transactional
    public ResponseEntity<LoginResponse> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        AuthProviderType providerType=authUtil.getProviderTypeFromRegistrationId(registrationId);
        String providerId=authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);

        User user=userRepository.findByProviderIdAndProviderType(providerId,providerType).orElse(null);

        String email=oAuth2User.getAttribute("email");
        User emailUser=userRepository.findByUsername(email).orElse(null);
        String name=oAuth2User.getAttribute("name");

        if(user==null && emailUser == null){
            String username=authUtil.determineUsernameFromOAuth2User(oAuth2User,registrationId,providerId);
            user= signUpInternal(new SignupRequest(username,null,name,Set.of(RoleType.CUSTOMER)),providerType,providerId);
        }
        else if (user != null) {
            if (email !=null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);

            }

        }
        else {
            throw new BadCredentialsException("This email is already registered with provider: "+emailUser.getProviderType());
        }
        LoginResponse loginResponse=new LoginResponse(authUtil.generateAccessToken(user),user.getId());
        return ResponseEntity.ok(loginResponse);
    }
}
