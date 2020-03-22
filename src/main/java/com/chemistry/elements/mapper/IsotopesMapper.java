package com.chemistry.elements.mapper;

import com.chemistry.elements.domain.Isotope;
import com.chemistry.elements.domain.IsotopeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IsotopesMapper {
    public Isotope mapToIsotope(IsotopeDto isotopeDto) {
        return new Isotope(isotopeDto.getId(), isotopeDto.getMassNumber(), isotopeDto.getName());
    }

    public IsotopeDto mapToIsotopeDto(Isotope isotope) {
        return new IsotopeDto(isotope.getId(), isotope.getMassNumber(), isotope.getName());
    }

    public List<Isotope> mapToIsotopeList(List<IsotopeDto> isotopeDto) {
        return isotopeDto.stream()
                .map(t -> new Isotope(t.getId(), t.getMassNumber(), t.getName()))
                .collect(Collectors.toList());
    }

    public List<IsotopeDto> mapToIsotopeDtoList(List<Isotope> isotopes) {
        return isotopes.stream()
                .map(t -> new IsotopeDto(t.getId(), t.getMassNumber(), t.getName()))
                .collect((Collectors.toList()));
    }

}
