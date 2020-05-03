package com.chemistry.elements.service;

import com.chemistry.elements.domain.*;
import com.chemistry.elements.domain.exception.ElementAlreadyExistException;
import com.chemistry.elements.domain.exception.EntityNotFoundException;
import com.chemistry.elements.mapper.ElementMapper;
import com.chemistry.elements.repository.ElementRepository;
import com.chemistry.elements.repository.IsotopeRepository;
import com.chemistry.elements.solver.FormulaSolver;
import com.chemistry.elements.validate.ElementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.*;

@Service
public class ElementService {
//    private final static String ELEMENT_NOT_EXIST = "Error: Element not exist: ";
//    private final static String ILLEGAL_CHARACTER = "Error: Illegal character in formula: ";

    @Autowired
    ElementRepository repository;

    @Autowired
    IsotopeRepository isotopeRepository;

    @Autowired
    ElementMapper mapper;

    @Autowired
    ElementValidator validator;

    @Autowired
    FormulaSolver solver;

    public List<ElementDto> getElements() {
        return mapper.mapToElementDtoList(repository.findAll());
    }

    public ElementDto getElement(String symbol) {
        return mapper.mapToElementDto(solver.getBySymbol(symbol));
    }

    public void saveElement(ElementDto elementDto) throws ElementAlreadyExistException {
        validator.checkType(elementDto.getType());
        Element element = mapper.mapToElement(elementDto);
        validator.checkElement(element.getName(), element.getSymbol());
        element.getIsotopes().forEach(i -> i.setElement(element));
        repository.save(element);
    }

    public List<FormulaDto> getFormula(String formula) {
        solver.solver(formula);

        return null;
    }
}
