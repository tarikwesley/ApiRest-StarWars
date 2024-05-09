package com.starwars.api.service;

import com.starwars.api.dto.PlanetStarWarsApiDTO;
import com.starwars.api.dto.PlanetStarWarsApiFilmsDTO;
import com.starwars.api.exception.StarWarsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsumerApiStarWarsServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ConsumerApiStarWarsService starWarsService;

    @BeforeEach
    void setUp() {
        starWarsService = new ConsumerApiStarWarsService(restTemplate);
    }

    @Test
    void shouldCorrectlyReturnAllPlanets() {
        PlanetStarWarsApiFilmsDTO planets = new PlanetStarWarsApiFilmsDTO();
        when(restTemplate.getForObject((String) isNull(), eq(PlanetStarWarsApiFilmsDTO.class))).thenReturn(planets);

        PlanetStarWarsApiFilmsDTO result = starWarsService.getAllPlanets();

        assertNotNull(result);
    }

    @Test
    void shouldThrowStarWarsExceptionWhenAnExternalCallToSearchForPlanetsReceivesAnError() {
        when(restTemplate.getForObject((String) isNull(), eq(PlanetStarWarsApiFilmsDTO.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(StarWarsException.class, () -> starWarsService.getAllPlanets());
    }

    @Test
    void shouldReturnPlanetCorrespondingToTheIdInformed() {
        Long id = 1L;
        PlanetStarWarsApiDTO planet = new PlanetStarWarsApiDTO();
        when(restTemplate.getForObject(anyString(), eq(PlanetStarWarsApiDTO.class), eq(id)))
                .thenReturn(planet);

        PlanetStarWarsApiDTO result = starWarsService.getById(id);

        assertEquals(planet, result);
    }

    @Test
    void shouldThrowStarWarsExceptionWhenNotFindingPlanetWithIdMatches() {
        Long id = 1L;
        when(restTemplate.getForObject(anyString(), eq(PlanetStarWarsApiDTO.class), eq(id)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(StarWarsException.class, () -> starWarsService.getById(id));
    }

    @Test
    void shouldReturnPlanetCorrespondingToTheNameInformed() {
        String name = "Tatooine";
        PlanetStarWarsApiFilmsDTO planet = new PlanetStarWarsApiFilmsDTO();
        when(restTemplate.getForObject(anyString(), eq(PlanetStarWarsApiFilmsDTO.class), eq(name)))
                .thenReturn(planet);

        PlanetStarWarsApiFilmsDTO result = starWarsService.getByName(name);

        assertEquals(planet, result);
    }

    @Test
    void shouldThrowStarWarsExceptionWhenNotFindingPlanetWithNameMatches() {
        String name = "Tatooine";
        when(restTemplate.getForObject(anyString(), eq(PlanetStarWarsApiFilmsDTO.class), eq(name)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(StarWarsException.class, () -> starWarsService.getByName(name));
    }
}