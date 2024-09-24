package com.isi.demo.departement;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
            @Valid @RequestBody DepartementRequest request, Authentication connectedUser
    ){
        return ResponseEntity.ok(service.saveDepartement(request,connectedUser));
    }
    @GetMapping
    public ResponseEntity<List<DepartementResponse>> listAllDepartement(Authentication connectedUser){
        return ResponseEntity.ok(service.listAllDepartement(connectedUser));
    }
    @GetMapping("/{departement-id}")
    public ResponseEntity<DepartementResponse> findDepartementById(
            @PathVariable("departement-id") Integer departementId, Authentication connectedUser
    ){
        return ResponseEntity.ok(service.findDepartementById(departementId,connectedUser));
    }
    @DeleteMapping("/{departement-id}")
    public ResponseEntity<Void> deleteDepartement(
            @PathVariable("departement-id") Integer departementId, Authentication connectedUser
    ){
            service.deleteDepartement(departementId,connectedUser);
            return ResponseEntity.accepted().build();
    }
    @PutMapping("/{departement-id}")
    public ResponseEntity<Void> updateDepartement(
            @PathVariable("departement-id") Integer id,
            @RequestBody @Valid UpdateDepartementRequest request, Authentication connectedUser
    ){
            request = new UpdateDepartementRequest(
                    id,
                    request.name());
            service.updateDepartement(request, connectedUser);
            return ResponseEntity.accepted().build();
    }

    @PatchMapping("/{departement-id}")
    public ResponseEntity<Void> update(
            @PathVariable("departement-id") Integer departementId,
            @Valid @RequestBody DepartementRequest request, Authentication connectedUser
    ){
            service.update(departementId,request, connectedUser);
            return ResponseEntity.noContent().build();
    }
}
