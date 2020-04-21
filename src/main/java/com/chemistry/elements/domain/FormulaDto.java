package com.chemistry.elements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FormulaDto {
    private double factor;
    private List<ElementShortDto> elements;
}
