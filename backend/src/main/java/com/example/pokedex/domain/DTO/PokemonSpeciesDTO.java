package com.example.pokedex.domain.DTO;

import com.example.pokedex.domain.valueObjects.FlavorTextEntry;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonSpeciesDTO {
    private List<FlavorTextEntry> flavor_text_entries;
}
