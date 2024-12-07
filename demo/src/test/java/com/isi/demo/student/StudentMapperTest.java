package com.isi.demo.student;

import com.isi.demo.departement.Departement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentRequestToStudent() {
        // Given
        StudentRequest request = new StudentRequest(
                1, // ID du student
                "Kaba", // Nom
                "abakaba@gmail.com", // Email
                1 // ID du département
        );

        // When
        Student student = mapper.toStudent(request);

        // Then
        assertEquals(request.id(), student.getId());
        assertEquals(request.name(), student.getName());
        assertEquals(request.email(), student.getEmail());
        assertNotNull(student.getDepartement()); // Vérifie que le département n'est pas null
        assertEquals(request.departementId(), student.getDepartement().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponse() {
        // Given
        Departement departement = new Departement(1, "Informatique");
        Student student = Student.builder()
                .id(1)
                .name("Kaba")
                .email("abakaba@gmail.com")
                .departement(departement)
                .build();

        // When
        StudentResponse response = mapper.fromStudent(student);

        // Then
        assertEquals(student.getId(), response.getId());
        assertEquals(student.getName(), response.getName());
        assertEquals(student.getEmail(), response.getEmail());
        assertEquals(departement.getName(), response.getDepartementName());
    }
}
