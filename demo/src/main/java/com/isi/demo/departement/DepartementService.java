package com.isi.demo.departement;


import com.isi.demo.exception.BusinessErrorCodes;
import com.isi.demo.exception.DepartementDeletionException;
import com.isi.demo.exception.OperationDepartementNotPermittedException;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartementService {

    private final DepartementRepository repository;
    private final DepartementMapper mapper;

    public Integer saveDepartement(DepartementRequest request) {
        if (repository.findByName(request.name()).isPresent()){
            throw new OperationDepartementNotPermittedException(BusinessErrorCodes.DUPLICATE_DEPARTMENT_NAME.getDescription());
        }
        var departement = repository.save(mapper.toDepartement(request));
        return departement.getId();
    }


    public void updateDepartement(UpdateDepartementRequest request) {
        var departement = repository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Le Departement non trouvé ID:: %s", request.id())
                ));
        mergeDepartement(departement,request);
        repository.save(departement);
    }

    private void mergeDepartement(Departement departement, UpdateDepartementRequest request) {
        if (StringUtils.isNotBlank(request.name()) &&
                !request.name().equals(departement.getName()) &&
                repository.findByName(request.name()).isPresent()){
            throw new OperationDepartementNotPermittedException(BusinessErrorCodes.DUPLICATE_DEPARTMENT_NAME.getDescription());
        }
        if(StringUtils.isNotBlank(request.name())){
            departement.setName(request.name());
        }
    }

    public DepartementResponse findDepartementById(Integer departementId) {
        return repository.findById(departementId)
                .map(mapper::fromDepartement)
                .orElseThrow(() -> new EntityNotFoundException("Aucune category avec ce ID:: " + departementId));
    }

    public void deleteDepartement(Integer id) {
        Departement departement = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cet departement n'existe pas"));

        if (!departement.getStudents().isEmpty()) {
            throw new DepartementDeletionException(BusinessErrorCodes.DEPARTMENT_DELETION_ERROR.getDescription());
        }
        repository.delete(departement);
    }


    public List<DepartementResponse> listAllDepartement() {
        return repository.findAll()
                .stream()
                .map(mapper::fromDepartement)
                .collect(Collectors.toList());
    }

    public void update(Integer departementId, DepartementRequest request) {
        Departement departement = repository.findById(departementId)
                .orElseThrow(() -> new EntityNotFoundException("Departement non trouvé" ));
        if(StringUtils.isNotBlank(request.name())){
            departement.setName(request.name());
        }
        repository.save(departement);

    }

}
