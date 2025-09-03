package com.example.pokedex.application.controller;

import com.example.pokedex.application.service.PokemonService;
import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/my-pokemon")
public class TeamController {
    private final PokemonService service;
    private final PokemonRepository repository;

    public TeamController(PokemonService service, PokemonRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Pokemon> listAllTasks(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon addNewPokemon(@RequestBody Pokemon newPokemon) {
        return repository.save(newPokemon);
    }
}