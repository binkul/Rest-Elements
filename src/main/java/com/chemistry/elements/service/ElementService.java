package com.chemistry.elements.service;

import com.chemistry.elements.domain.*;
import com.chemistry.elements.domain.exception.ElementAlreadyExistException;
import com.chemistry.elements.domain.exception.EntityNotFoundException;
import com.chemistry.elements.mapper.ElementMapper;
import com.chemistry.elements.repository.ElementRepository;
import com.chemistry.elements.repository.IsotopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService {
    private final static String ELEMENT_NOT_EXIST = "Error: Element not exist: ";
    private final static String ILLEGAL_CHARACTER = "Error: Illegal character in formula: ";

    @Autowired
    ElementRepository repository;
    @Autowired
    IsotopeRepository isotopeRepository;
    @Autowired
    ElementMapper mapper;

    public List<ElementDto> getElements() {
        return mapper.mapToElementDtoList(repository.findAll());
    }

    public void saveElement(ElementDto elementDto) throws ElementAlreadyExistException {
        checkType(elementDto.getType());
        Element element = mapper.mapToElement(elementDto);
        checkExist(element.getName(), element.getSymbol());
        element.getIsotopes().forEach(i -> i.setElement(element));
        repository.save(element);
    }

    public List<FormulaDto> getFormula(String formula) {
        int i = 0;
        char chr;

        while (i < formula.length()) {
            chr = formula.charAt(i);

            if (chr >= 'A' && chr <= 'Z') {

                String symbol = getSymbol(formula, i);
                i += symbol.length();
                System.out.println(symbol);

            } else if(chr >= '0' && chr <= '9') {

                String value = getValue(formula, i);
                i += value.length();
                System.out.println(value);

            } else {
                throw new EntityNotFoundException(ILLEGAL_CHARACTER + chr);
            }
        }

        return null;
    }

    private void checkExist(String name, String symbol) {
        if (repository.existsElementByNameOrSymbol(name, symbol)) {
            throw new ElementAlreadyExistException("Element: " + name + ", symbol: " + symbol + " already exists!");
        }
    }

    private void checkType(String type) {
        if (!Type.isPresent(type)) throw new EntityNotFoundException("Could not find elements typ: '" + type + "'. Available are METAL, SEMI_METAL, NON_METAL, INERT_GAS.");
    }


    private String getSymbol(String formula, int i) {
        StringBuilder result = new StringBuilder();
        result.append(formula.charAt(i));

        i++;
        if (i < formula.length()) {
            char chr = formula.charAt(i);
            if (chr >= 'a' && chr <= 'z') {
                result.append(formula.charAt(i));
            }
        }
        return result.toString();
    }

    private String getValue(String formula, int i) {
        StringBuilder result = new StringBuilder();

        char chr = formula.charAt(i);
        while (chr >= '0' && chr <= '9') {
            result.append(chr);
            i++;
            if (i >= formula.length()) break;
            chr = formula.charAt(i);
        }
        return result.toString();
    }
}
