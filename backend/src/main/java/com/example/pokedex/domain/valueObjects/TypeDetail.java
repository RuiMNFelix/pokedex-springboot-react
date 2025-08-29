package com.example.pokedex.domain.valueObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TypeDetail {
    private String name;
    private String url;
}
