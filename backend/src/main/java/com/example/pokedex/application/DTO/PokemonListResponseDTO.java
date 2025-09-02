package com.example.pokedex.application.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PokemonListResponseDTO {
    private int count;
    private List<PokemonListItemDTO> results;
}
