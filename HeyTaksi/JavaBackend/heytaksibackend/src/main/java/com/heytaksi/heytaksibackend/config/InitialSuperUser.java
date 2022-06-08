package com.heytaksi.heytaksibackend.config;


import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class InitialSuperUser implements ApplicationRunner {

    @Autowired
    private AppUserRepository appUserRepository;

    public InitialSuperUser(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public void run(ApplicationArguments args) {

        AppUser initialSuperUser = new AppUser();
        initialSuperUser.setUserEmail("superuser@heytaksi.com");
        initialSuperUser.setUserPassword("Super123*");
        initialSuperUser.setRole("ROLE_SUPER");
        initialSuperUser.setFullname("Full Name");
        initialSuperUser.setUserPhoneNumber("+9077771123");
        LocalDateTime localNow = LocalDateTime.now();
        ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
        ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
        initialSuperUser.setRegisteredDate(zonedIST.toLocalDateTime());

        appUserRepository.save(initialSuperUser);
    }

}
