package com.example.pokedex.domain.DTO;

import lombok.Data;

@Data
public class PokemonDTO {
    private int number;
    private String name;
    private int height;
    private int weight;
    private String sprite;
}
