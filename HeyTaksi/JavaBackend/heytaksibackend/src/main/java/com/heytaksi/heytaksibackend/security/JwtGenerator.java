package com.heytaksi.heytaksibackend.security;

import com.heytaksi.heytaksibackend.model.AppUser;
import com.heytaksi.heytaksibackend.repository.AppUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtGenerator {

    @Autowired
    private AppUserRepository appUserRepository;


    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    public String generate(AppUser appUser) {

        Claims claims = Jwts.claims()
                .setSubject(appUser.getUserEmail());
        claims.put("role", appUser.getRole());
        claims.put("email", appUser.getUserEmail());
        claims.put("password", appUser.getUserPassword());
        claims.put("fullname", appUser.getFullname());

        claims.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .setIssuedAt(new Date(System.currentTimeMillis()));

        String tmpToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, "20A8D4339E67E6A5D8854E7AAE0A82C778D075D0D6E1BD0B993038176A6A255A")
                .compact();

        try{
            AppUser tmpUserToken = new AppUser();
            tmpUserToken.setUserEmail(appUser.getUserEmail());
            tmpUserToken.setUserPassword(appUser.getUserPassword());
            tmpUserToken.setFullname(appUser.getFullname());
            tmpUserToken.setLastLogin(appUser.getLastLogin());
            tmpUserToken.setUserPhoneNumber(appUser.getUserPhoneNumber());
            tmpUserToken.setId(appUser.getId());
            tmpUserToken.setToken(tmpToken);
            tmpUserToken.setRole(appUser.getRole().toUpperCase());

            LocalDateTime localNow = LocalDateTime.now();
            ZonedDateTime zonedUTC = localNow.atZone(ZoneId.of("UTC"));
            ZonedDateTime zonedIST = zonedUTC.withZoneSameInstant(ZoneId.of("Europe/Istanbul"));
            tmpUserToken.setLastLogin(zonedIST.toLocalDateTime());

            appUserRepository.save(tmpUserToken);

        }catch (Exception e){
            System.out.println("Error when insert usertoken to DB");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.out.println(e.getStackTrace());
        }

        return tmpToken;
    }
}
