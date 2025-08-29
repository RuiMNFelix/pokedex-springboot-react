package com.example.pokedex.domain.valueObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonFlavorDescription {
    private String text;
    private String version;
    private String language;
}

