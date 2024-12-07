package com.isi.demo.student;


import com.isi.demo.departement.Departement;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toStudent(StudentRequest request) {
        if (request == null){
            return null ;
        }
        return Student.builder()
                .id(request.id())
                .name(request.name())
                .email(request.email())
                .departement(Departement.builder().id(request.id()).build())
                .build();
    }
    public StudentResponse fromStudent(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .departementName(student.getDepartement() != null ? student.getDepartement().getName(): null)
                .build();
    }


}
