package com.pankaj.StayNest.security;

import com.pankaj.StayNest.entity.User;
import com.pankaj.StayNest.entity.type.Authprovidertype;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class AuthUtil {
    @Value("${jwt.secretkey}")
    private String secretKey;

    public SecretKey getSecretKey( ){
      return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String genrateAccesstoken(User user) {
      return Jwts.builder().subject(user.getUsername())
              .claim("userId",user.getId().toString())
              .issuedAt(new Date())
              .expiration(new Date(System.currentTimeMillis()+1000*60*10)).signWith(getSecretKey()).compact();
    }

    public String getUserNameByToken(String token){
        return Jwts.parser().verifyWith(getSecretKey())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();

    }

   public Authprovidertype getAuthProviderTypeById(String registerId){
        return switch (registerId.toLowerCase()){
            case "google" -> Authprovidertype.GOOGLE;
            case "github" -> Authprovidertype.GITHUB;
            case "facebook" -> Authprovidertype.FACEBOOK;
            default -> throw new IllegalArgumentException("Unsupported OAuth2 provider: " + registerId);
        };
   }

   public String getProvidedIdByOAuth2User(OAuth2User oAuth2User,String registrationId){
       String providerId= switch (registrationId.toLowerCase()){
           case "google" -> oAuth2User.getAttribute("sub");
           )
           case "github" -> oAuth2User.getAttribute("id").toString();

           default -> {
               throw new IllegalArgumentException("Unsupported OAuth2 provider: " + registrationId);
           }
       };
       if (providerId == null || providerId.isBlank()) {

           throw new IllegalArgumentException("Unable to determine providerId for OAuth2 login");
       }
       return providerId;

   }

    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User, String registrationId, String providerId) {
        String name= oAuth2User.getAttribute("name");
        if (name != null && !name.isBlank()) {
            return name;
        }
        return switch (registrationId.toLowerCase()) {
            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
            default -> providerId;
        };
    }
}
