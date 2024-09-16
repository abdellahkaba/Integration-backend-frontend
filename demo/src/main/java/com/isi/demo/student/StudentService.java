package com.isi.demo.student;


import com.isi.demo.departement.Departement;
import com.isi.demo.departement.DepartementRepository;
import com.isi.demo.exception.BusinessErrorCodes;
import com.isi.demo.exception.OperationNotPermittedException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final DepartementRepository departementRepository;
    private final StudentMapper mapper ;


        public Integer addStudent(StudentRequest request) {
            if (repository.findByEmail(request.email()).isPresent()){
                throw new OperationNotPermittedException(BusinessErrorCodes.DUPLICATE_EMAIL.getDescription());
            }
            var departement = departementRepository.findById(request.departementId())
                    .orElseThrow(() -> new EntityNotFoundException("Departement non trouvé"));
            var student = mapper.toStudent(request);
            student.setDepartement(departement);
            student = repository.save(student);
            return student.getId();
        }

    public List<StudentResponse> listAllStudent() {
        return repository.findAll()
                .stream()
                .map(mapper::fromStudent)
                .collect(Collectors.toList());
    }
    public StudentResponse findStudentById(Integer studentId) {
        return repository.findById(studentId)
                .map(mapper::fromStudent)
                .orElseThrow(() -> new EntityNotFoundException(" L'Étudiant non trouvé !"));
    }

    public void updateStudent(UpdateStudentRequest request) {
        var student = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("L'Étudiant non trouvé !"));
        // si l'id de departement n'existe pas
        if (request.departementId() != null) {
            var departement = departementRepository.findById(request.departementId())
                    .orElseThrow(() -> new EntityNotFoundException("Ce departement n'existe pas ID : " + request.departementId()));
            student.setDepartement(departement);
        }

        mergeStudent(student,request);
        repository.save(student);
    }

    private void mergeStudent(Student student, UpdateStudentRequest request) {
        if (StringUtils.isNotBlank(request.email()) &&
            !request.email().equals(student.getEmail()) &&
                repository.findByEmail(request.email()).isPresent()){
            throw new OperationNotPermittedException(BusinessErrorCodes.DUPLICATE_EMAIL.getDescription());
        }

        if (StringUtils.isNotBlank(request.name())){
            student.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.email())){
            student.setEmail(request.email());
        }
    }

    public void deleteStudent(Integer id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cet Etudiant n'existe pas avec ID : " + id));
        repository.delete(student);
    }

    public void updateStudent(Integer id, StudentRequest request) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Etudiant non trouvé"));

        // Mise à jour partielle des champs
        if(StringUtils.isNotBlank(request.name())){
            student.setName(request.name());
        }
        if (StringUtils.isNotBlank(request.email())) {
            student.setEmail(request.email());
        }
        if (request.departementId() != null) {
            Departement departement = departementRepository.findById(request.departementId())
                    .orElseThrow(() -> new EntityNotFoundException("Departement not found"));
            student.setDepartement(departement);
        }
        // Sauvegarder les changements
        repository.save(student);
    }

}
