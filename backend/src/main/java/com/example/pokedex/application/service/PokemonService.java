package com.example.pokedex.application.service;

import com.example.pokedex.domain.DTO.PokemonListItemDTO;
import com.example.pokedex.domain.DTO.PokemonListResponseDTO;
import com.example.pokedex.domain.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

}
