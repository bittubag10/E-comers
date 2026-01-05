package com.example.E_comers.DTO.Request;

import com.example.E_comers.ENUM.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerRequest {


    private String name;

    private String email;

    private Long phone;

    private Gender gender;
}
