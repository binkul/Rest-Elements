package com.chemistry.elements.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ElementDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("namePl")
    private String namePl;
    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("type")
    private String type;
    @JsonProperty("number")
    private int number;
    @JsonProperty("mass")
    private double mass;
    @JsonProperty("isotope")
    private List<IsotopeDto> isotopes;
}
