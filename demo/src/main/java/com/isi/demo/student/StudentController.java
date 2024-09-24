package com.isi.demo.student;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
@Tag(name = "Students")
public class StudentController {

    private final StudentService service ;

    @PostMapping
    public ResponseEntity<Integer> addStudent(
            @Valid @RequestBody StudentRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.addStudent(request,connectedUser));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponse>> listAllStudent(Authentication connectedUser){
        return ResponseEntity.ok(service.listAllStudent(connectedUser));
    }
    @GetMapping("/{student-id}")
    public ResponseEntity<StudentResponse> findStudentById(
            @PathVariable("student-id") Integer studentId,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findStudentById(studentId,connectedUser));
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable("student-id") Integer id,
            @RequestBody @Valid UpdateStudentRequest request,
            Authentication connectedUser
    ){
        request = new UpdateStudentRequest(
                id,
                request.name(),
                request.email(),
                request.departementId()
        );
        service.updateStudent(request,connectedUser);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("student-id") Integer id,
            Authentication connectedUser
    ){
        service.deleteStudent(id,connectedUser);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{student-id}")
    public ResponseEntity<Void> updateStudent (
            @PathVariable("student-id") Integer studentId,
            @Valid @RequestBody StudentRequest request, Authentication connectedUser
    ){
        service.updateStudent(studentId,request,connectedUser);
        return ResponseEntity.noContent().build();
    }
}
