package it.epicode.d5w7.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
