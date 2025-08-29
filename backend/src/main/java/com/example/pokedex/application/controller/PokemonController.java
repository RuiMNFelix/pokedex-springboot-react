package com.example.pokedex.application.controller;

import com.example.pokedex.application.service.PokemonService;
import com.example.pokedex.domain.DTO.PokemonDetailsDTO;
import com.example.pokedex.domain.DTO.PokemonListItemDTO;
import com.example.pokedex.domain.DTO.PokemonListResponseDTO;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
    private final PokemonRepository repository;
    private final PokemonService service;

    public PokemonController( PokemonRepository repository, PokemonService service){
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    public PokemonListResponseDTO listAllPokemon(
            @RequestParam(defaultValue = "151") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return service.getPokemonList(limit, offset);
    }

    @GetMapping("/{nameOrId}")
    public PokemonListItemDTO getPokemonByNameOrId(@PathVariable String nameOrId){
        return service.getPokemonByNameOrId(nameOrId);
    }

    @GetMapping("/details/{nameOrId}")
    public PokemonDetailsDTO getPokemonDetails(@PathVariable String nameOrId){
        return service.getPokemonDetails(nameOrId);
    }
}