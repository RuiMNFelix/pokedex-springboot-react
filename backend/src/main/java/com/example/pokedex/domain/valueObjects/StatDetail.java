package com.example.pokedex.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class StatDetail {
    private String name;
    private String url;
}
