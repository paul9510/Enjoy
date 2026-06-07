package org.example.enjoy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter

public class AuthRequest {

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Le format de l'email est invalide")
    private String email;
    @NotBlank(message = "Le mot de passe est obligatoire")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Le mot de passe doit contenir au moins 8 caractères, une majuscule et un chiffre"
    )
    private String password;

}
