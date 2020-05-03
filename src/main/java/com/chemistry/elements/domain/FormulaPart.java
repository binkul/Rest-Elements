package com.chemistry.elements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FormulaPart {
    String description;
    FormulaType type;
    double value;

    @Override
    public String toString() {
        return "FormulaPart" +
                "{description='" + description + '\'' +
                ", type=" + type +
                ", value=" + value +
                "}\n";
    }
}
