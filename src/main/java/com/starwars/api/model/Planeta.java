package com.starwars.api.model;

import com.starwars.api.dto.PlanetaDTO;
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
@Document("planeta")
public class Planeta {
    @Id
    private Long id;
    private String nome;
    private String clima;
    private String terreno;
    private Integer aparicoesEmFilmes;

    public Planeta(PlanetaDTO planetaDTO) {
        this.nome = planetaDTO.getNome();
        this.clima = planetaDTO.getClima();
        this.terreno = planetaDTO.getTerreno();
    }
}