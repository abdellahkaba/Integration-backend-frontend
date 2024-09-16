package com.isi.demo.student;

import jakarta.validation.constraints.NotBlank;

public record UpdateStudentRequest(
        Integer id,
        String name,
        String email,
        Integer departementId
) {
}
