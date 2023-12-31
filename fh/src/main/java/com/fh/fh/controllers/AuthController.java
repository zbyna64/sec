package com.fh.fh.controllers;

import com.fh.fh.models.ExpirationResponse;
import com.fh.fh.models.TokenResponse;
import com.fh.fh.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.Instant;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    @GetMapping("/basic/token")
    @SecurityRequirement(name = "basic Authentication")
    public ResponseEntity<TokenResponse> token(Authentication authentication) throws ParseException {
        String token = tokenService.generateToken(authentication);
        Instant expiresAt = tokenService.tokenExpiration("Bearer " + token);
        return ResponseEntity.ok().body(new TokenResponse(token, expiresAt));
    }

    @GetMapping("/ldap/token")
    @SecurityRequirement(name = "basic Authentication")
    public ResponseEntity<TokenResponse> ldapToken(Authentication authentication) throws ParseException {
        String token = tokenService.generateToken(authentication);
        Instant expiresAt = tokenService.tokenExpiration("Bearer " + token);
        return ResponseEntity.ok().body(new TokenResponse(token, expiresAt));
    }

    @SecurityRequirement(name = "bearer Authentication")
    @GetMapping("/token/expiration")
    public ResponseEntity<ExpirationResponse> expiration(HttpServletRequest request) throws ParseException {
        String encodedToken = request.getHeader("Authorization");
        return ResponseEntity.ok().body(tokenService.getExpirationTime(encodedToken));
    }


}
