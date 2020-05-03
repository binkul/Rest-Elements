package com.chemistry.elements.validate;

import com.chemistry.elements.domain.Type;
import com.chemistry.elements.domain.exception.ElementAlreadyExistException;
import com.chemistry.elements.domain.exception.EntityNotFoundException;
import com.chemistry.elements.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElementValidator {

    @Autowired
    ElementRepository repository;

    public void checkElement(String name, String symbol) {
        if (repository.existsElementByNameOrSymbol(name, symbol)) {
            throw new ElementAlreadyExistException("Element: " + name + ", symbol: " + symbol + " already exists!");
        }
    }

    public void checkType(String type) {
        if (!Type.isPresent(type)) throw new EntityNotFoundException("Could not find elements typ: '" + type + "'. Available are METAL, SEMI_METAL, NON_METAL, INERT_GAS.");
    }


}
