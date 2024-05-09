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
public class PlanetDTO {
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotBlank(message = "Climate cannot be empty!")
    private String climate;
    @NotBlank(message = "Terrain cannot be empty!")
    private String terrain;
}
