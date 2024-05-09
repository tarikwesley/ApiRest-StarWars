package com.starwars.api.service;

import com.starwars.api.exception.StarWarsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.starwars.api.dto.PlanetStarWarsApiDTO;
import com.starwars.api.dto.PlanetStarWarsApiFilmsDTO;

@Service
public class ConsumerApiStarWarsService {

    private final RestTemplate restTemplate;

    @Value("${starWarsApiBaseUrl}")
    private String starWarsApiBaseUrl;

    public ConsumerApiStarWarsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PlanetStarWarsApiFilmsDTO getAllPlanets() {
        try {
            return this.restTemplate.getForObject(starWarsApiBaseUrl, PlanetStarWarsApiFilmsDTO.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new StarWarsException("Error when listing planets: " + e.getMessage());
        }
    }

    public PlanetStarWarsApiDTO getById(Long id) {
        String apiUrl = starWarsApiBaseUrl + "{id}/";
        try {
            return this.restTemplate.getForObject(apiUrl, PlanetStarWarsApiDTO.class, id);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new StarWarsException("Error when listing planet by id: " + e.getMessage());
        }
    }

    public PlanetStarWarsApiFilmsDTO getByName(String nome) {
        String apiUrl = starWarsApiBaseUrl + "?search={nome}";
        try {
            return this.restTemplate.getForObject(apiUrl, PlanetStarWarsApiFilmsDTO.class, nome);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new StarWarsException("Error when listing planet by name: " + e.getMessage());
        }
    }
}
