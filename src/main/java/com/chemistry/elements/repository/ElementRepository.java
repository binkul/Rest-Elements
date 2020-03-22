package com.chemistry.elements.repository;

import com.chemistry.elements.domain.Element;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElementRepository extends CrudRepository<Element, Long> {

    @Override
    Element save(Element element);

    @Override
    void deleteById(Long id);

    @Override
    List<Element> findAll();

    @Override
    Optional<Element> findById(Long id);

    Optional<Element> findByName(String name);
}
