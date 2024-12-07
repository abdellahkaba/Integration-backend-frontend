package com.isi.demo.departement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DepartementMapperTest {

    private DepartementMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new DepartementMapper();
    }

    @Test
    public void shouldMapDepartementRequestToDepartement() {
        // Given
        DepartementRequest request = new DepartementRequest(
                1,      // ID du département
                "Physical" // Nom du département
        );

        // When
        Departement departement = mapper.toDepartement(request);

        // Then
        assertNotNull(departement); // Vérifie que l'objet n'est pas null
        assertEquals(request.id(), departement.getId());
        assertEquals(request.name(), departement.getName());
    }

    @Test
    public void shouldMapDepartementToDepartementResponse() {
        // Given
        Departement departement = new Departement(
                1,      // ID du département
                "Physical" // Nom du département
        );

        // When
        DepartementResponse response = mapper.fromDepartement(departement);

        // Then
        assertNotNull(response); // Vérifie que la réponse n'est pas null
        assertEquals(departement.getId(), response.getId());
        assertEquals(departement.getName(), response.getName());
    }
}
