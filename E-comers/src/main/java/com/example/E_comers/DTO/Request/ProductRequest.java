package com.example.E_comers.DTO.Request;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {
    private String name;

    private String description;

    private Long price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
