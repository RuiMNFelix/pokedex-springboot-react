package com.example.pokedex.application.service;

import com.example.pokedex.domain.DTO.PokemonDetailsDTO;
import com.example.pokedex.domain.DTO.PokemonListItemDTO;
import com.example.pokedex.domain.DTO.PokemonListResponseDTO;
import com.example.pokedex.domain.DTO.PokemonSpeciesDTO;
import com.example.pokedex.domain.valueObjects.FlavorTextEntry;
import com.example.pokedex.domain.valueObjects.PokemonFlavorDescription;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;

    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonListResponseDTO getPokemonList(int limit, int offset){
        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/")
                .pathSegment("pokemon")
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .toUriString();

        return restTemplate.getForObject(apiUrl, PokemonListResponseDTO.class);
    }

    public PokemonListItemDTO getPokemonByNameOrId(String nameOrId) {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + nameOrId;
        PokemonListItemDTO response = restTemplate.getForObject(apiUrl, PokemonListItemDTO.class);

        if (response == null) {
            throw new RuntimeException("No data from PokeAPI for " + nameOrId);
        }

        response.setUrl(apiUrl);
        return response;
    }
    public PokemonDetailsDTO getPokemonDetails(String nameOrId){
        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + nameOrId;
        String speciesUrl = "https://pokeapi.co/api/v2/pokemon-species/" + nameOrId;

        PokemonDetailsDTO details = restTemplate.getForObject(pokemonUrl, PokemonDetailsDTO.class);
        PokemonSpeciesDTO species = restTemplate.getForObject(speciesUrl, PokemonSpeciesDTO.class);

        if (details == null || species == null) {
            throw new RuntimeException("No data from PokeAPI for " + nameOrId);
        }

        FlavorTextEntry flavorEntry = species.getFlavor_text_entries().stream().findFirst().orElse(null);
        PokemonFlavorDescription flavorDescription = null;
        if (flavorEntry != null) {
            flavorDescription = new PokemonFlavorDescription(
                    flavorEntry.getFlavorText(),
                    flavorEntry.getVersion() != null ? flavorEntry.getVersion().getName() : "Unknown",
                    flavorEntry.getLanguage() != null ? flavorEntry.getLanguage().getName() : "Unknown"
            );
        }

        details.setDescription(flavorDescription);
        return details;
    }
}