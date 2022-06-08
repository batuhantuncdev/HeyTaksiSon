package com.heytaksi.heytaksibackend.controller;


import com.heytaksi.heytaksibackend.dto.RegisterRequestDTO;
import com.heytaksi.heytaksibackend.dto.RegisterResponseDTO;
import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityExistsException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RequestMapping(path = "/app/")
@Controller
public class RegisterController extends BaseController {

    @Autowired
    AppUserService appUserService;


    public RegisterController(ModelMapper mapper) {
        super(mapper);
    }

    @PostMapping(value = REGISTER, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponseDTO> register(@Validated @RequestBody RegisterRequestDTO registerRequestDTO) {

        Optional<AppUser> foundAppUser = appUserService.getAppUserByEmailForRegistration(registerRequestDTO.getUserEmail());

        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));

        if(foundAppUser.isPresent() && foundAppUser.get().getUserEmail().equals(registerRequestDTO.getUserEmail())) {
            throw new EntityExistsException("This user has already registered");
        }

        final AppUser appUser = map(registerRequestDTO, AppUser.class);
        appUser.setRegisteredDate(zonedIST.toLocalDateTime());

        final AppUser savedAppUser = appUserService.saveAppUser(appUser);
        final RegisterResponseDTO responseDTO = map(savedAppUser, RegisterResponseDTO.class);

        return ResponseEntity.ok(responseDTO);
    }
}
