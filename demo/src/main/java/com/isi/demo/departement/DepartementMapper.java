package com.isi.demo.departement;

import org.springframework.stereotype.Component;

@Component
public class DepartementMapper {
    public Departement toDepartement(DepartementRequest request) {
        if (request == null){
            return null ;
        }
        return Departement.builder()
                .id(request.id())
                .name(request.name())
                .build();
    }
    public DepartementResponse fromDepartement(Departement departement) {
        return DepartementResponse.builder()
                .id(departement.getId())
                .name(departement.getName())
                .build();
    }
}
