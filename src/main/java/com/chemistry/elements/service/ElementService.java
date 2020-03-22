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

    private void checkExist(String name, String symbol) {
        if (repository.existsElementByNameOrSymbol(name, symbol)) {
            throw new ElementAlreadyExistException("Element: " + name + ", symbol: " + symbol + " already exists!");
        }
    }

    private void checkType(String type) {
        if (!Type.isPresent(type)) throw new EntityNotFoundException("Could not find elements typ: '" + type + "'. Available are METAL, SEMI_METAL, NON_METAL, INERT_GAS.");
    }
}
