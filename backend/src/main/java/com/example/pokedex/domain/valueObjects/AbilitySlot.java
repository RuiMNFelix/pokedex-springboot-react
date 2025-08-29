package com.example.pokedex.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AbilitySlot {
    private Ability ability;
    @JsonProperty("is_hidden")
    private boolean isHidden;
    private int slot;
}
