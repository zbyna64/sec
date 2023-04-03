package com.fh.fh.security;

import com.nimbusds.jwt.JWTClaimNames;
import com.nimbusds.jwt.JWTClaimsSet;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    String scope = authentication.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(" "));
    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("zbyna")
        .issuedAt(now)
        .expiresAt(now.plus(expiracy, ChronoUnit.MINUTES))
        .subject(authentication.getName())
        .claim("scope", scope)
        .build();
    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }
}
