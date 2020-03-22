package com.chemistry.elements.service;

import com.chemistry.elements.domain.Element;
import com.chemistry.elements.domain.ElementDto;
import com.chemistry.elements.domain.Type;
import com.chemistry.elements.domain.exception.EntityNotFoundException;
import com.chemistry.elements.domain.exception.MissedArgumentException;
import com.chemistry.elements.mapper.ElementMapper;
import com.chemistry.elements.repository.ElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService {
    @Autowired
    ElementRepository repository;
    @Autowired
    ElementMapper mapper;

    public List<ElementDto> getElements() {
        return mapper.mapToElementDto(repository.findAll());
    }

    public void saveElement(ElementDto elementDto) {
        checkType(elementDto.getType());
        try {
            Element element = mapper.mapToElement(elementDto);
            element.getIsotopes().forEach(i -> i.setElement(element));
            repository.save(element);
        } catch (Exception ex) {
            throw new MissedArgumentException("Missed field or wrong argument in element description.");
        }
    }

    private void checkType(String type) {
        if (!Type.isPresent(type)) throw new EntityNotFoundException("Could not find elements typ: '" + type + "'. Available are METAL, SEMI_METAL, NON_METAL, INERT_GAS.");

/*
        try {
            type = elementDto.getType().toString();
        } catch (Exception ex) {
            throw new EntityNotFoundException("Could not find elements typ: '" + type + "'. Available are METAL, SEMI_METAL, NON_METAL, INERT_GAS.");
        }
*/
    }
}
