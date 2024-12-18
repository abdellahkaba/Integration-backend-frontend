package com.isi.demo.departement;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceTest {

    // Quel service on veut tester ?

    @InjectMocks
    private DepartementService departementService;
    @Mock
    private DepartementMapper departementMapper;
    @Mock
    private DepartementRepository departementRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_create_departement() {
        // Given ou Arrange
        DepartementRequest request = new DepartementRequest(
                1,
                "Physical"
        );
        Departement departement = new Departement(
                2,
                "Physical"
        );

        // Simuler le comportement des méthodes du repository et du mapper

        // Quand la méthode toDepartement de l'objet mocké departementMapper est appelée avec l'argument request,
        // retourne immédiatement l'objet departement, sans exécuter la logique réelle de toDepartement.
        when(departementRepository.findByName(request.name())).thenReturn(Optional.empty()); // Aucun département avec ce nom
        when(departementMapper.toDepartement(request)).thenReturn(departement); // Mapper correctement
        when(departementRepository.save(departement)).thenReturn(departement); // Sauvegarde du département

        // When // Action
        Integer response = departementService.saveDepartement(request);

        // Then ou Assert
        // assertEquals compare deux valeurs (expect: valeur attendue et actual: valeur réelle)
        assertEquals(departement.getId(), response); // Vérifie que ID retourné correspond
        verify(departementRepository).findByName(request.name()); // Vérifie que findByName a été appelé
        verify(departementMapper,times(1)).toDepartement(request);
        verify(departementRepository).save(departement);          // Vérifie que save a été appelé
    }

    @Test
    public void should_update_departement_successfully() {
        // Given
        Integer departementId = 1;
        UpdateDepartementRequest request = new UpdateDepartementRequest(
                departementId,
                "Informatique"
        );
        Departement existingDepartement = new Departement(departementId, "Science");

        when(departementRepository.findById(departementId)).thenReturn(Optional.of(existingDepartement));
        when(departementRepository.findByName(request.name())).thenReturn(Optional.empty());

        // When
        departementService.updateDepartement(request);

        // Then
        assertEquals("Informatique", existingDepartement.getName()); // Vérifie que le nom a été mis à jour
        verify(departementRepository, times(1)).findById(departementId);
        verify(departementRepository, times(1)).findByName(request.name());
        verify(departementRepository, times(1)).save(existingDepartement);
    }

    @Test
    public void should_throw_exception_when_departement_not_found() {
        // Given
        Integer departementId = 99;
        UpdateDepartementRequest request = new UpdateDepartementRequest(
                departementId,
                "Informatique"
        );

        when(departementRepository.findById(departementId)).thenReturn(Optional.empty());

        // When & Then
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> departementService.updateDepartement(request)
        );

        assertEquals("Le Departement non trouvé ID:: 99", exception.getMessage());
        verify(departementRepository, times(1)).findById(departementId);
        verifyNoMoreInteractions(departementRepository); // Pas d'autres interactions avec le repository
    }



}