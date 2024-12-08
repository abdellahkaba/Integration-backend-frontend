package com.isi.demo.student;

import com.isi.demo.departement.Departement;
import com.isi.demo.departement.DepartementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    DepartementRepository departementRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_add_student_successfully() {
        // Given
        Integer departementId = 1;
        Integer studentId = 123;
        StudentRequest request = new StudentRequest(
                studentId,
                "John Doe",
                "john.doe@example.com",
                departementId
        );
        Departement departement = new Departement(departementId, "Informatique");
        Student student = new Student(null, "John Doe", "john.doe@example.com", null);
        Student savedStudent = new Student(studentId, "John Doe", "john.doe@example.com", departement);

        when(departementRepository.findById(departementId)).thenReturn(Optional.of(departement));
        when(studentRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(studentMapper.toStudent(request)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);

        // When
        Integer response = studentService.addStudent(request);

        // Then
        assertEquals(studentId, response);
        verify(departementRepository, times(1)).findById(departementId);
        verify(studentRepository, times(1)).findByEmail(request.email());
        verify(studentMapper, times(1)).toStudent(request);
        verify(studentRepository, times(1)).save(student);
    }

}