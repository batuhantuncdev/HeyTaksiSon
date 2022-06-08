package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.AppUserRequestDTO;
import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserServiceImpl implements AppUserService {

    private static final String EXISTING_APPUSER = "Existing user with this email.";

    @Autowired
    AppUserRepository appUserRepository;

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser getAppUserByEmail(String email) {
        return appUserRepository.findAppUserByUserEmail(email).orElseThrow(() ->
                new EntityNotFoundException("Appuser not found with this email: " + email));
    }


    @Override
    public Optional<AppUser> getAppUserByEmailForRegistration(String email) {
        return appUserRepository.findAppUserByUserEmail(email);
    }

    @Override
    public AppUser getAppUserById(long id) {
        return appUserRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Appuser not found with this id: " + id));
    }

    @Override
    public List<AppUser> getUserList() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser updateAppUser(AppUser appUser, AppUserRequestDTO appUserRequestDTO) {
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        appUser.setUpdatedAt(zonedIST.toLocalDateTime());
        appUser.setCurrentLocationX(appUserRequestDTO.getCurrentLocationX());
        appUser.setCurrentLocationY(appUserRequestDTO.getCurrentLocationY());
        appUser.setIsAvailable(appUserRequestDTO.getIsAvailable());

        return appUserRepository.save(appUser);
    }

    @Override
    public void deleteAppUser(long id) {
        final AppUser willDeleteEntity = appUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("App User not found with id: " + id));
        appUserRepository.delete(willDeleteEntity);
    }

    @Override
    public List<AppUser> getDriverList() {
        return appUserRepository.findAll().stream().filter(appUser -> Objects.equals(appUser.getRole(), "DRIVER")).collect(Collectors.toList());
    }

    @Override
    public Optional<List<AppUser>> getAvailableDriverList(String role, Boolean isAvailable) {
        return appUserRepository.findAllByRoleAndIsAvailable(role, isAvailable);
    }
}
