// JWTUtil : 0.11.5
package com.avoworld.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${spring.jwt.secret}")
    private String secret;

    private Key key;

    @PostConstruct
    public void init() {
        System.out.println("Initializing JWTUtil...");
        System.out.println("Secret: " + secret);

        byte[] byteSecretKey = Decoders.BASE64.decode(secret);
        System.out.println("Decoded key length: " + byteSecretKey.length);

        if (byteSecretKey.length < 32) {
            throw new IllegalArgumentException("The decoded key is not long enough. It must be at least 32 bytes.");
        }
        key = Keys.hmacShaKeyFor(byteSecretKey);
        System.out.println("Key has been initialized.");
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("email", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public String createJwt(String email, Long expiredMS) {
        if (key == null) {
            throw new IllegalStateException("Key has not been initialized.");
        }

        Claims claims = Jwts.claims();
        claims.put("email", email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredMS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
