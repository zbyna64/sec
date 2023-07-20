package com.fh.fh.security;

import com.fh.fh.models.ExpirationResponse;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtEncoder encoder;
    @Value("${jwt.expirationHours}")
    private int expirationHours;

    public String generateToken(Authentication authentication) {

        final Instant now = Instant.now();
        String scope = authentication.getAuthorities()
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("dCOS")
                .issuedAt(now)
                .expiresAt(now.plus(expirationHours, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public ExpirationResponse getExpirationTime(String token) throws ParseException {
        Instant expireAt = tokenExpiration(token);
        if (expireAt.isBefore(Instant.now())) {
            return new ExpirationResponse("JWT token is expired");
        }
        return new ExpirationResponse(expireAt);

    }

    public Instant tokenExpiration(String token) throws ParseException {
        Jwt jwtToken = decode(token);
        Instant expiresAt = jwtToken.getExpiresAt();
        return expiresAt;
    }

    private Jwt decode(String encodedToken) throws ParseException {
        String parsedToken = getToken(encodedToken);
        JWT jwt = JWTParser.parse(parsedToken);

        Map<String, Object> headers = new LinkedHashMap<>(jwt.getHeader().toJSONObject());
        Map<String, Object> claims = new HashMap<>();

        for (String key : jwt.getJWTClaimsSet().getClaims().keySet()) {
            Object value = jwt.getJWTClaimsSet().getClaims().get(key);
            if (key.equals("exp") || key.equals("iat")) {
                value = ((Date) value).toInstant();
            }
            claims.put(key, value);
        }
        return Jwt.withTokenValue(parsedToken)
                .headers(h -> h.putAll(headers))
                .claims(c -> c.putAll(claims))
                .build();
    }

    private String getToken(String encodedToken) {
        return Arrays.stream(encodedToken.split(" "))
                .filter(t -> !t.contains("Bearer"))
                .map(Objects::toString)
                .collect(Collectors.toList())
                .get(0);
    }
}
