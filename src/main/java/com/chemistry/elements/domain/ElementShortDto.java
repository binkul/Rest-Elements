package com.chemistry.elements.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ElementShortDto {
    private String name;
    private String namePl;
    private String symbol;
    private int number;
    private double mass;
}
