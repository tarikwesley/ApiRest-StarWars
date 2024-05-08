package com.starwars.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.starwars.api.model.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {
    Planeta findByNome(String nome);
}
