package com.example.testauthservice.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Configuration
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    private byte[] secret ;

    private final Key key;

    public JwtTokenProvider(@Value("${app.jwtSecret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
        this.secret = jwtSecret.getBytes();
        //this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Collection<GrantedAuthority> userRole = (Collection<GrantedAuthority>) authentication.getAuthorities();
        List<String> roles = userRole.stream().map(role -> role.getAuthority()).toList();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", roles.get(0))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.substring(7)).getBody().getSubject();
    }

    public boolean validateToken(String token) throws Exception {
        Jws<Claims> jws;

        byte[] secret = "randomKeyForHS512Algorithm123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890".getBytes();

        try{
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            jws = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);


        }catch (ExpiredJwtException e) {
            // The JWT expired and is no longer valid
            throw new Exception(e);
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            // The JWT was not correctly constructed and should be rejected
            throw new Exception(e);
        } catch (JwtException e) {
            // Any other JWT related exception
            throw new Exception(e);
        } catch (IllegalArgumentException e) {
            // The JWT token is null or empty and should be rejected
            throw new Exception(e);
        }

        return true;
    }
}
