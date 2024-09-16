package com.isi.demo.student;


import com.isi.demo.departement.Departement;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;
}
