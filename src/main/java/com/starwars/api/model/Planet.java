package com.starwars.api.model;

import com.starwars.api.dto.PlanetDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "planet")
public class Planet {
    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private Integer filmsAppearances;

    public Planet(PlanetDTO planetDTO) {
        this.name = planetDTO.getName();
        this.climate = planetDTO.getClimate();
        this.terrain = planetDTO.getTerrain();
    }
}