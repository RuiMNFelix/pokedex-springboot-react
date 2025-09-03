package com.example.pokedex.application.DTO;

import com.example.pokedex.domain.valueObjects.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class PokemonDTO {
    private int id;
    private String name;
    @JsonProperty("flavour_text_entries")
    private PokemonFlavorDescription description;
    private List<TypeSlot> types;
    private int hpStat;
    private int attackStat;
    private int defenseStat;
    private int spAttackStat;
    private int spDefenseStat;
    private int speedStat;
    private String gender;
    private String ability;
    private String nature;
    private int level;
    private int height;
    private int weight;
    private Sprite sprites;
}
