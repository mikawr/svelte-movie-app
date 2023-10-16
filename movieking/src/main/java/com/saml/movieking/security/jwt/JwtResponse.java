package com.saml.movieking.security.jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Getter
@Value
public class JwtResponse {
    String token;
    String type = "Bearer";
    Long id;
    String username;
    String email;
    List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}