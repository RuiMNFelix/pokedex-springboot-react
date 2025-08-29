package com.example.pokedex.application.service;

import com.example.pokedex.domain.DTO.PokemonDetailsDTO;
import com.example.pokedex.domain.DTO.PokemonListItemDTO;
import com.example.pokedex.domain.DTO.PokemonListResponseDTO;
import com.example.pokedex.domain.DTO.PokemonSpeciesDTO;
import com.example.pokedex.domain.valueObjects.FlavorTextEntry;
import com.example.pokedex.domain.valueObjects.PokemonFlavorDescription;
import com.example.pokedex.domain.valueObjects.Sprite;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    private final RestTemplateBuilder restTemplateBuilder;

    public PokemonService(RestTemplate restTemplate, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplate;
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public PokemonListResponseDTO getPokemonList(int limit, int offset){
        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/")
                .pathSegment("pokemon")
                .queryParam("limit", limit)
                .queryParam("offset", offset)
                .toUriString();

        return restTemplate.getForObject(apiUrl, PokemonListResponseDTO.class);
    }

    public PokemonListItemDTO getPokemonByNameOrId(String nameOrId){
        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/")
                .pathSegment("pokemon", nameOrId)
                .toUriString();

        Map response = restTemplate.getForObject(apiUrl, Map.class);

        PokemonListItemDTO dto = new PokemonListItemDTO();
        assert response != null;
        dto.setName((String) response.get("name"));
        dto.setUrl("https://pokeapi.co/api/v2/pokemon/" + nameOrId);

        return dto;
    }

    public PokemonDetailsDTO getPokemonDetails(String nameOrId){
        String apiUrl = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/")
                .pathSegment("pokemon", nameOrId)
                .toUriString();

        PokemonDetailsDTO response = restTemplate.getForObject(apiUrl, PokemonDetailsDTO.class);

        String apiUrl2 = UriComponentsBuilder.fromHttpUrl("https://pokeapi.co/api/v2/")
                .pathSegment("pokemon-species", nameOrId)
                .toUriString();

        PokemonSpeciesDTO response2 = restTemplate.getForObject(apiUrl2, PokemonSpeciesDTO.class);

        if (response == null || response2 == null) {
            throw new RuntimeException("No data from PokeAPI for " + nameOrId);
        }

        PokemonDetailsDTO dto = new PokemonDetailsDTO();
        dto.setId(response.getId());
        dto.setName(response.getName());

        FlavorTextEntry flavorDescriptionEntry = response2.getFlavor_text_entries().stream().findFirst().orElse(null);
        if (flavorDescriptionEntry != null) {
            PokemonFlavorDescription flavorDescription = new PokemonFlavorDescription(
                    flavorDescriptionEntry.getFlavorText(),
                    flavorDescriptionEntry.getVersion() != null ? flavorDescriptionEntry.getVersion().getName() : "Unknown",
                    flavorDescriptionEntry.getLanguage() != null ? flavorDescriptionEntry.getLanguage().getName() : "Unknown"
            );
        }

        dto.setDescription(flavorDescriptionEntry);
        dto.setHeight(response.getHeight());
        dto.setWeight(response.getWeight());
        dto.setTypes(response.getTypes());
        dto.setAbilities(response.getAbilities());
        dto.setStats(response.getStats());
        dto.setSprites(response.getSprites());
        return dto;
    }

}
