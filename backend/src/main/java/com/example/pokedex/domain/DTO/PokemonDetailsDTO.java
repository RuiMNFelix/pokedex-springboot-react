package com.example.pokedex.domain.DTO;

import com.example.pokedex.domain.valueObjects.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonDetailsDTO {
    private int id;
    private String name;
    @JsonProperty("flavour_text_entries")
    private FlavorTextEntry description;
    private List<AbilitySlot> abilities;
    private List<TypeSlot> types;
    private List<StatSlot> stats;
    private int height;
    private int weight;
    private Sprite sprites;
}
