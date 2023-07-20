package com.fh.fh.security;

import com.fh.fh.models.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    @Value("${jwt.expiracy}")
    private int expiracy;


    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();
        String scope = Arrays.stream(authentication.getAuthorities().toString().split(","))
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("dCOS")
                .issuedAt(now)
//        .expiresAt()
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateToken() {

        Instant now = Instant.now();
        String scope = "ROLE_ADMIN";
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("dCOS")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiracy))
                .subject("ADMIN")
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
