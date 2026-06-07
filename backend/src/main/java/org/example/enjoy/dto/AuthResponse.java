package org.example.enjoy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private Long id;
    private String email;
    private String role;
    private String token;
}
