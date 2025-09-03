package com.example.pokedex.application.controller;

import com.example.pokedex.application.service.PokemonService;
import com.example.pokedex.domain.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Validated
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
    List<Pokemon> listAllTasks(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Pokemon getPokemonById(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Pokemon addNewPokemon(@RequestBody Pokemon newPokemon) {
        return repository.save(newPokemon);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePokemon(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    Pokemon replacePokemon(@PathVariable Long id, @RequestBody Pokemon newPokemon) {
        return repository.findById(id).map(pokemon -> {
            pokemon.setName(newPokemon.getName());
            pokemon.setGender(newPokemon.getGender());
            pokemon.setAbility(newPokemon.getAbility());
            pokemon.setNature(newPokemon.getNature());
            return repository.save(pokemon);
        }).orElseGet(() -> {
            return repository.save(newPokemon);
        });
    }
}