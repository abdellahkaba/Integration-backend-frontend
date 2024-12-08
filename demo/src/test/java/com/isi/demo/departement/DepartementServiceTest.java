package com.isi.demo.departement;

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

}