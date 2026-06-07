package org.example.enjoy.service;

import org.example.enjoy.dto.AuthResponse;
import org.example.enjoy.exception.AuthException;
import org.example.enjoy.exception.BadRequestException;
import org.example.enjoy.model.User;
import org.example.enjoy.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,  JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User registerUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            throw new BadRequestException("Email ou password non renseigné");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new BadRequestException("Cet email est déjà utilisé");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public AuthResponse login(String email, String password) {

        if (email.isEmpty() || password.isEmpty()) {
            throw new AuthException("Identifiants incorrects");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                AuthResponse authResponse = new AuthResponse();
                authResponse.setId(user.getId());
                authResponse.setEmail(user.getEmail());
                authResponse.setRole(user.getRole());
                authResponse.setToken(jwtService.generateToken(user.getEmail()));
                return authResponse;
            }
            throw new AuthException("Identifiants incorrects");

        } else {
            throw new AuthException("Identifiants incorrects");
        }
    }


}
