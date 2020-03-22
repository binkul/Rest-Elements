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
    @Autowired
    IsotopesMapper isotopesMapper;

    public Element mapToElement(final ElementDto elementDto) {
        return new Element(elementDto.getId(), elementDto.getName(), elementDto.getNamePl(), elementDto.getSymbol(), Type.valueOf(elementDto.getType().toUpperCase()),
                elementDto.getNumber(), elementDto.getMass(), isotopesMapper.mapToIsotopeList(elementDto.getIsotopes()));
    }

    public ElementDto mapToElementDto(final Element element) {
        return new ElementDto(element.getId(), element.getName(), element.getNamePl(), element.getSymbol(), element.getName(),
                element.getNumber(), element.getMass(), isotopesMapper.mapToIsotopeDtoList(element.getIsotopes()));
    }

    public List<ElementDto> mapToElementDtoList(List<Element> elements) {
        return elements.stream()
                .map(t -> new ElementDto(t.getId(), t.getName(), t.getNamePl(), t.getSymbol(), t.getType().name(), t.getNumber(), t.getMass(), isotopesMapper.mapToIsotopeDtoList(t.getIsotopes())))
                .collect(Collectors.toList());
     }
}
