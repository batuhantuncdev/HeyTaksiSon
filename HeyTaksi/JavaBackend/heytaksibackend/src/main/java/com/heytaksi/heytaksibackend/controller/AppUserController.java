package com.heytaksi.heytaksibackend.controller;

import com.heytaksi.heytaksibackend.dto.AppUserRequestDTO;
import com.heytaksi.heytaksibackend.dto.AppUserResponseDTO;
import com.heytaksi.heytaksibackend.dto.DeleteResponseDTO;
import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.service.AppUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(path = "/app/")
@Controller
@Validated
public class AppUserController extends BaseController {

    @Autowired
    AppUserService appUserService;

    public AppUserController(ModelMapper mapper) {
        super(mapper);
    }

    @GetMapping(value = "user/{email}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserResponseDTO> getUserByEmail(@PathVariable(value = "email") String email){
        final AppUser fetchedUser = appUserService.getAppUserByEmail(email);
        final AppUserResponseDTO appUserResponseDTO = map(fetchedUser, AppUserResponseDTO.class);
        return ResponseEntity.ok(appUserResponseDTO);
    }

    @GetMapping(value = "user/getUserList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUserResponseDTO>> getUserList() {
        final List<AppUser> appUserList = appUserService.getUserList();
        final List<AppUserResponseDTO> responseDTOList = mapAll(appUserList, AppUserResponseDTO.class);
        return ResponseEntity.ok(responseDTOList);
    }

    @GetMapping(value = "user/driver/getDriverList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUserResponseDTO>> getDriverList() {
        final List<AppUser> appUserList = appUserService.getDriverList();
        final List<AppUserResponseDTO> responseDTOList = mapAll(appUserList, AppUserResponseDTO.class);
        return ResponseEntity.ok(responseDTOList);
    }

    @PutMapping(value = "user/update/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserResponseDTO> updateAppUser(
            @PathVariable(value = "userId") long userId, @Validated @RequestBody AppUserRequestDTO appUserRequestDTO) {

        final AppUser appUser = appUserService.getAppUserById(userId);
        final AppUser updatedAppUser = appUserService.updateAppUser(appUser, appUserRequestDTO);
        final AppUserResponseDTO appUserResponseDTO = map(updatedAppUser, AppUserResponseDTO.class);
        return ResponseEntity.ok(appUserResponseDTO);
    }

    @GetMapping(value = "user/driver/getAvailableDriverList", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUserResponseDTO>> getAvailableDriverList() {
        final Optional<List<AppUser>> driverList = appUserService.getAvailableDriverList("CUSTOMER", true);
        if(driverList.isPresent()) {
            final List<AppUserResponseDTO> responseDTOList = mapAll(driverList.get(), AppUserResponseDTO.class);
            return ResponseEntity.ok(responseDTOList);
        } else {
            throw new EntityNotFoundException("Driver doesn't exist");
        }
    }

    @DeleteMapping(value = "user/delete/{userId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<DeleteResponseDTO> deleteAppUser(@PathVariable(value = "userId") long userId) {
        appUserService.deleteAppUser(userId);
        DeleteResponseDTO deleteResponseDTO = new DeleteResponseDTO();
        deleteResponseDTO.setResult(true);
        deleteResponseDTO.setDescription("User Deleted Successfully.");
        return ResponseEntity.ok(deleteResponseDTO);
    }
}
