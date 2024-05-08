package com.starwars.api.service;

import com.starwars.api.exception.StarWarsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.starwars.api.dto.PlanetaStarWarsApiDTO;
import com.starwars.api.dto.PlanetaStarWarsApiFilmsDTO;

@Service
public class ConsumerApiStarWarsService {

    private final RestTemplate restTemplate;

    @Value("${starWarsApiBaseUrl}")
    private String starWarsApiBaseUrl;

    public ConsumerApiStarWarsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlanetaStarWarsApiFilmsDTO listarPlanetas() {
        try {
            return this.restTemplate.getForObject(starWarsApiBaseUrl, PlanetaStarWarsApiFilmsDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new StarWarsException("Error ao listar planetas: " + e.getMessage());
        }
    }

    public PlanetaStarWarsApiDTO listarPorId(Long id) {
        String apiUrl = starWarsApiBaseUrl + "{id}/";
        try {
            return this.restTemplate.getForObject(apiUrl, PlanetaStarWarsApiDTO.class, id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new StarWarsException("Error ao listar planeta por id: " + e.getMessage());
        }
    }

    public PlanetaStarWarsApiFilmsDTO listarPorNome(String nome) {
        String apiUrl = starWarsApiBaseUrl + "?search={nome}";
        try {
            return this.restTemplate.getForObject(apiUrl, PlanetaStarWarsApiFilmsDTO.class, nome);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new StarWarsException("Error ao listar planeta por nome: " + e.getMessage());
        }
    }
}
