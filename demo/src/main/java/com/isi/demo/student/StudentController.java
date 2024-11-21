package com.isi.demo.student;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
            @Valid @RequestBody StudentRequest request
    ){
        return ResponseEntity.ok(service.addStudent(request));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponse>> listAllStudent(){
        return ResponseEntity.ok(service.listAllStudent());
    }
    @GetMapping("/{student-id}")
    public ResponseEntity<StudentResponse> findStudentById(
            @PathVariable("student-id") Integer studentId
    ){
        return ResponseEntity.ok(service.findStudentById(studentId));
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<Void> updateStudent(
            @PathVariable("student-id") Integer id,
            @RequestBody @Valid UpdateStudentRequest request
    ){
        request = new UpdateStudentRequest(
                id,
                request.name(),
                request.email(),
                request.departementId()
        );
        service.updateStudent(request);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable("student-id") Integer id
    ){
        service.deleteStudent(id);
        return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{student-id}")
    public ResponseEntity<Void> updateStudent (
            @PathVariable("student-id") Integer studentId,
            @Valid @RequestBody StudentRequest request
    ){
        service.updateStudent(studentId,request);
        return ResponseEntity.noContent().build();
    }
}
