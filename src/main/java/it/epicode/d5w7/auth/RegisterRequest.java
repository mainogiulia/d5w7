package it.epicode.d5w7.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
