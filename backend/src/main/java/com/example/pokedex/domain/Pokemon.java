package com.example.pokedex.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String ability;
    @Enumerated(EnumType.STRING)
    private Nature nature;
}
