package com.isi.demo.departement;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartementResponse {
    private Integer id;
    private String name;
}
