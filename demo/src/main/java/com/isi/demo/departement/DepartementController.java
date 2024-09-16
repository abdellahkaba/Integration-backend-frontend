package com.isi.demo.departement;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("departements")
@Tag(name = "Departements")
public class DepartementController {

    private final DepartementService service;

    @PostMapping
    public ResponseEntity<Integer> saveDepartement(
            @Valid @RequestBody DepartementRequest request
    ){
        return ResponseEntity.ok(service.saveDepartement(request));
    }
    @GetMapping
    public ResponseEntity<List<DepartementResponse>> listAllDepartement(){
        return ResponseEntity.ok(service.listAllDepartement());
    }
    @GetMapping("/{departement-id}")
    public ResponseEntity<DepartementResponse> findDepartementById(
            @PathVariable("departement-id") Integer departementId
    ){
        return ResponseEntity.ok(service.findDepartementById(departementId));
    }
    @DeleteMapping("/{departement-id}")
    public ResponseEntity<Void> deleteDepartement(
            @PathVariable("departement-id") Integer departementId
    ){
            service.deleteDepartement(departementId);
            return ResponseEntity.accepted().build();
    }
    @PutMapping("/{departement-id}")
    public ResponseEntity<Void> updateDepartement(
            @PathVariable("departement-id") Integer id,
            @RequestBody @Valid UpdateDepartementRequest request
    ){
            request = new UpdateDepartementRequest(
                    id,
                    request.name());
            service.updateDepartement(request);
            return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{departement-id}")
    public ResponseEntity<Void> update(
            @PathVariable("departement-id") Integer departementId,
            @Valid @RequestBody DepartementRequest request
    ){
            service.update(departementId,request);
            return ResponseEntity.noContent().build();
    }
}
