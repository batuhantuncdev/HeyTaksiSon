package com.heytaksi.heytaksibackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginResponseDTO {

//    @Email(message = "Email is not correct format")
//    @NotBlank
    private String token;
    private String error;
    private RegisterResponseDTO registerResponseDTO;

}
