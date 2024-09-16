package com.isi.demo.departement;

import jakarta.validation.constraints.NotBlank;

public record DepartementRequest(
        Integer id,
        @NotBlank(message = "Donner le nom du departement")
        String name

) {
}
