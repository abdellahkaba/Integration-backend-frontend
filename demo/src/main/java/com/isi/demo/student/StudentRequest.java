package com.isi.demo.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StudentRequest(
        Integer id,
        @NotBlank(message = "Le nom est requis")
        String name,
        @NotBlank(message = "Le mail est requis")
        @Email(message = "Le mail n'est pas bien formaté")
        String email,
        @NotNull(message = "Donner un departement pour cet étudiant")
        Integer departementId
) {
}
