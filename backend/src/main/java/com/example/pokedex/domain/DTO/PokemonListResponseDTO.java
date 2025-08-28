package com.example.pokedex.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PokemonListResponseDTO {
    private int count;
    private List<PokemonListItemDTO> results;
}
