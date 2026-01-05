package com.example.E_comers.DTO.Response;

import com.example.E_comers.ENUM.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomerResponse {

    private String name;

    private Long phone;

    private Gender gender;
}
