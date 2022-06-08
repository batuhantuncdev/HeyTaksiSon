package com.heytaksi.heytaksibackend.security;


import com.heytaksi.heytaksibackend.model.AppUser;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {

    private String secret = "20A8D4339E67E6A5D8854E7AAE0A82C778D075D0D6E1BD0B993038176A6A255A";

    public AppUser validate(String token) {

        AppUser appUser = null;

        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            appUser = new AppUser();

            appUser.setUserEmail(body.getSubject());
            appUser.setRole((String) body.get("role"));
            appUser.setUserEmail((String) body.get("email"));
            appUser.setUserPassword((String) body.get("password"));

        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT exception");
        }catch (IllegalArgumentException ex){
            System.out.println("Jwt claims string is empty");
        }

        return appUser;
    }


}
