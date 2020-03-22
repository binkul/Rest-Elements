package com.chemistry.elements.repository;

import com.chemistry.elements.domain.Element;
import com.chemistry.elements.domain.Isotope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IsotopeRepository extends CrudRepository<Isotope, Long> {

    @Override
    Isotope save(Isotope isotope);
}
