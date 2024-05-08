package com.starwars.api.service;

import com.starwars.api.dto.PlanetaStarWarsApiDTO;
import com.starwars.api.dto.PlanetaStarWarsApiFilmsDTO;
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
    void deveRetornarCorretamenteTodosOsPlanetas() {
        PlanetaStarWarsApiFilmsDTO planetas = new PlanetaStarWarsApiFilmsDTO();
        when(restTemplate.getForObject((String) isNull(), eq(PlanetaStarWarsApiFilmsDTO.class))).thenReturn(planetas);

        PlanetaStarWarsApiFilmsDTO result = starWarsService.listarPlanetas();

        assertNotNull(result);
    }

    @Test
    void deveLancarErrorQuandoNaChamadaExternaParaBuscarPlanetasObtiverAlgumError() {
        when(restTemplate.getForObject((String) isNull(), eq(PlanetaStarWarsApiFilmsDTO.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(StarWarsException.class, () -> starWarsService.listarPlanetas());
    }

    @Test
    void deveRetornaPlanetaCorrespondeteAoIdInformado() {
        Long id = 1L;
        PlanetaStarWarsApiDTO planeta = new PlanetaStarWarsApiDTO();
        when(restTemplate.getForObject(anyString(), eq(PlanetaStarWarsApiDTO.class), eq(id)))
                .thenReturn(planeta);

        PlanetaStarWarsApiDTO result = starWarsService.listarPorId(id);

        assertEquals(planeta, result);
    }

    @Test
    void deveLancarErrorQuandoNaoEncontrarPlanetaComIdCorresponde() {
        Long id = 1L;
        when(restTemplate.getForObject(anyString(), eq(PlanetaStarWarsApiDTO.class), eq(id)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(StarWarsException.class, () -> starWarsService.listarPorId(id));
    }

    @Test
    void deveRetornarPlanetaCorrespondenteAoNomeInformado() {
        String nome = "Tatooine";
        PlanetaStarWarsApiFilmsDTO planeta = new PlanetaStarWarsApiFilmsDTO();
        when(restTemplate.getForObject(anyString(), eq(PlanetaStarWarsApiFilmsDTO.class), eq(nome)))
                .thenReturn(planeta);

        PlanetaStarWarsApiFilmsDTO result = starWarsService.listarPorNome(nome);

        assertEquals(planeta, result);
    }

    @Test
    void deveLancarErrorQuandoNaoEncontrarPlanetaComNomeCorresponde() {
        String nome = "Tatooine";
        when(restTemplate.getForObject(anyString(), eq(PlanetaStarWarsApiFilmsDTO.class), eq(nome)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(StarWarsException.class, () -> starWarsService.listarPorNome(nome));
    }
}