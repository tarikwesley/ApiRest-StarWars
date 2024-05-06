package com.starwars.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanetaDTO {
    @NotBlank(message = "Nome não pode ser vazio!")
    private String nome;
    @NotBlank(message = "Clima não pode ser vazio!")
    private String clima;
    @NotBlank(message = "Terreno não pode ser vazio!")
    private String terreno;
}
