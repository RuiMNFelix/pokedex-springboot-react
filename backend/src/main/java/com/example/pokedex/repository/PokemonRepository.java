package com.example.pokedex.repository;

import com.example.pokedex.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
