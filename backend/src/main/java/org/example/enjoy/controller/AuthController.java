package org.example.enjoy.controller;

import jakarta.validation.Valid;
import org.example.enjoy.dto.AuthRequest;
import org.example.enjoy.dto.AuthResponse;
import org.example.enjoy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest authRequest) {
        userService.registerUser(authRequest.getEmail(),  authRequest.getPassword());
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        AuthResponse response = userService.login(authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok().body(response);
    }
}
