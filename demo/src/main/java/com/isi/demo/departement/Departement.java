package com.isi.demo.departement;


import com.isi.demo.student.Student;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Departement(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;
    @OneToMany(mappedBy = "departement")
    private List<Student> students;


}
