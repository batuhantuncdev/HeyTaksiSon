package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserRequestDTO {
    private String role;
    private Double currentLocationX;
    private Double currentLocationY;
    private Boolean isAvailable;
}
