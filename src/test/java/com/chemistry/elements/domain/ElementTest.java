package com.chemistry.elements.domain;

import com.chemistry.elements.repository.ElementRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElementTest {

    @Autowired
    ElementRepository elementRepository;

    @Test
    public void writeAndReadTableTest() {
        //Given
        Element hydrogen = new Element();
        hydrogen.setName("Hydrogen");
        hydrogen.setSymbol("H");
        hydrogen.setMass(1.00784);
        hydrogen.setType(Type.NON_METAL);
        hydrogen.setNumber(1);

        Isotope deuter = new Isotope();
        deuter.setElement(hydrogen);
        deuter.setMassNumber(2);

        Isotope tryt = new Isotope();
        tryt.setElement(hydrogen);
        tryt.setMassNumber(3);

        hydrogen.getIsotopes().add(deuter);
        hydrogen.getIsotopes().add(tryt);

        //When
        elementRepository.save(hydrogen);
        long id = hydrogen.getId();

        //Then
        Assert.assertNotEquals(0, id);
        elementRepository.deleteById(id);

    }

    @Test
    public void readElementTest() {
        // Given
        List<Element> elements = elementRepository.findAll();

        // When
        int count = elements.size();
        System.out.println(elements.get(0));
        System.out.println(elements.get(0).getIsotopes());

        // Then
        Assert.assertEquals(1, count);
    }
}