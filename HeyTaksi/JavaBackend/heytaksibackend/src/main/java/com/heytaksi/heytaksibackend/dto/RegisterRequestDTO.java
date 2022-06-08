package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterRequestDTO {
    private String userEmail;
    private String userPassword;
    private String username;
    private String role;
    private String phoneNumber;
    private String fullname;
    private Double currentLocationX;
    private Double currentLocationY;
}
