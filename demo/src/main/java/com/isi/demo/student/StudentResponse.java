package com.isi.demo.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {

    private Integer id;
    private String name;
    private String email;
    private String departementName;
}
