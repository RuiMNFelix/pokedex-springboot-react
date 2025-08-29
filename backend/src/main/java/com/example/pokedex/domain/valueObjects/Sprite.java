package com.example.pokedex.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprite {
    @JsonProperty("front_default")
    private String frontDefault;

    @JsonProperty("back_default")
    private String backDefault;

    @JsonProperty("front_shiny")
    private String frontShiny;

    @JsonProperty("back_shiny")
    private String backShiny;
}
