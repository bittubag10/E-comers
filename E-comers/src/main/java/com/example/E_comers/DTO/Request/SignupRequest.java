package com.example.E_comers.DTO.Request;

import com.example.E_comers.ENUM.RoleType;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SignupRequest {
    private String username;

    private String password;

    private String name;

    private Set<RoleType> roles=new HashSet<>();
}
