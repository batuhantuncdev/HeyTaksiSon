package com.heytaksi.heytaksibackend.service;

import com.heytaksi.heytaksibackend.dto.AppUserRequestDTO;
import com.heytaksi.heytaksibackend.model.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserService {

    AppUser saveAppUser(AppUser appUser);

    AppUser getAppUserByEmail(String email);

    Optional<AppUser> getAppUserByEmailForRegistration(String email);

    AppUser getAppUserById(long id);

    List<AppUser> getUserList();

    Optional<List<AppUser>> getAvailableDriverList(String role, Boolean isAvailable);

    AppUser updateAppUser(AppUser appUser, AppUserRequestDTO appUserRequestDTO);

    void deleteAppUser(long id);

    List<AppUser> getDriverList();
}
