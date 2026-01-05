package com.example.E_comers.Model;

import com.example.E_comers.ENUM.Gender;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    private Long phone;

    @OneToOne
    @MapsId
    private User user;

    @Enumerated(EnumType.STRING)
    private Gender gender;
}
