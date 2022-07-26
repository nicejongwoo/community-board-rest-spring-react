package com.example.communityboardrestspringreact.security.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtTokenService {

    @Value("${jwt.accessExpirationsMs}")
    private Long TOKEN_PERIOD;

    private Key key;

    private final CustomUserDetailsService customUserDetailsService;
    private final String jwtSecret;

    public JwtTokenService(CustomUserDetailsService customUserDetailsService, @Value("${jwt.secret}") String jwtSecret) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtSecret = jwtSecret;
    }

    @PostConstruct
    void init() {
        byte[] sha = Sha512DigestUtils.sha(jwtSecret.getBytes());
        String encodedJwtSecret = Base64.getEncoder().encodeToString(sha);
        this.key = Keys.hmacShaKeyFor(encodedJwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        }catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        }catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        }catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        }catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }

        return false;
    }
    public String createAccessToken(Authentication authenticate) {
        CustomUserDetails principal = (CustomUserDetails) authenticate.getPrincipal();
        return createAccessTokenFromEmail(principal.getAccount().getEmail());
    }

    private String createAccessTokenFromEmail(String email) {
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_PERIOD))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(this.getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getEmailFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpirationFromToken(String token) {
        return getClaimsFromToken(token).getExpiration();
    }

    private String getSubject(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

}
