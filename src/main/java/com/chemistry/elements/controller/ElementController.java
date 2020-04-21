package com.chemistry.elements.controller;

import com.chemistry.elements.domain.ElementDto;
import com.chemistry.elements.domain.FormulaDto;
import com.chemistry.elements.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/element")
public class ElementController {
    @Autowired
    ElementService service;

    @GetMapping("/all")
    List<ElementDto> getElements() {
        return service.getElements();
    }

    @GetMapping("/{formula}")
    List<FormulaDto> getFormula(@PathVariable String formula) {
        return service.getFormula(formula);
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addElement(@RequestBody ElementDto elementDto) {
        service.saveElement(elementDto);
    }
}
