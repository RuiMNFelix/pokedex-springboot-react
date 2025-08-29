package com.example.pokedex.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pokemonId;
    private String name;
    private String type;
    private String ability;
    private String nature;
    private int level;
    private String imageUrl;
}
