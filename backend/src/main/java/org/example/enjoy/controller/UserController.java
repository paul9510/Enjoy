package org.example.enjoy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/profile")
    public ResponseEntity<?> profile() {
        return ResponseEntity.ok("Accès VIP accordé ! Le filtre fonctionne à la perfection.");
    }
}
