package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserResponseDTO {
    private Long id;
    private String role;
    private String userEmail;
    private Double currentLocationX;
    private Double currentLocationY;
    private Boolean isAvailable;
}
