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
    private int number;
    private String type;
    private int level;
    private String imageUrl;
}
