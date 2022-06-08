package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterResponseDTO {
    private Long id;
    private String userEmail;
    private String userPassword;
    private String username;
    private String role;
    private String phoneNumber;
    private String fullname;
    private Boolean isOnline;
    private Double currentLocationX;
    private Double currentLocationY;
}
