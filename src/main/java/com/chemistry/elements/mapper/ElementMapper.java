package com.chemistry.elements.mapper;

import com.chemistry.elements.domain.Element;
import com.chemistry.elements.domain.ElementDto;
import com.chemistry.elements.domain.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ElementMapper {
    @Autowired IsotopesMapper isotopesMapper;

    public Element mapToElement(final ElementDto elementDto) {
        return new Element(elementDto.getId(), elementDto.getName(), elementDto.getSymbol(), Type.valueOf(elementDto.getType().toUpperCase()),
                elementDto.getNumber(), elementDto.getMass(), isotopesMapper.mapToIsotopeList(elementDto.getIsotopes()));
    }

    public List<ElementDto> mapToElementDto(List<Element> elements) {
        return elements.stream()
                .map(t -> new ElementDto(t.getId(), t.getName(), t.getSymbol(), t.getType().name(), t.getNumber(), t.getMass(), isotopesMapper.mapToIsotopeDtoList(t.getIsotopes())))
                .collect(Collectors.toList());
    }
}
