package com.example.E_comers.DTO.Response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
    private String name;

    @Column(length = 2000)
    private String description;

    private Long price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
