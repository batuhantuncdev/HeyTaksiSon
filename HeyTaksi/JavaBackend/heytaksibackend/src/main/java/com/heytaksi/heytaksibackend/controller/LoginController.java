package com.heytaksi.heytaksibackend.controller;

import com.heytaksi.heytaksibackend.dto.LoginRequestDTO;
import com.heytaksi.heytaksibackend.dto.LoginResponseDTO;
import com.heytaksi.heytaksibackend.dto.RegisterResponseDTO;
import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.security.JwtGenerator;
import com.heytaksi.heytaksibackend.service.AppUserService;
import com.heytaksi.heytaksibackend.service.AppUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(path = "/app/")
@Controller
@Validated
public class LoginController extends BaseController {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private AppUserService appUserService;

    public LoginController(ModelMapper mapper) {
        super(mapper);
    }

    @PostMapping(value = LOGIN, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponseDTO> generate(@Validated @RequestBody LoginRequestDTO loginRequestDTO) {
        AppUser foundAppUser = appUserService.getAppUserByEmail(loginRequestDTO.getEmail());

        if (foundAppUser.getUserEmail().equals(loginRequestDTO.getEmail()) && foundAppUser.getUserPassword().equals(loginRequestDTO.getPassword())) {
            foundAppUser.setToken(jwtGenerator.generate(foundAppUser));

            final RegisterResponseDTO registerResponseDTO = map(foundAppUser, RegisterResponseDTO.class);
            final LoginResponseDTO loginResponseDTO = map(registerResponseDTO, LoginResponseDTO.class);
            loginResponseDTO.setToken(foundAppUser.getToken());

            return ResponseEntity.ok(loginResponseDTO);
        } else {
            throw new NullPointerException("You need Registration");
        }
    }
}
