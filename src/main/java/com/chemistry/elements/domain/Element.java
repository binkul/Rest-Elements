package com.chemistry.elements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "elements")
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String namePl;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Type type = Type.NON_METAL;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private double mass;

    @Column
    @OneToMany(targetEntity = Isotope.class,
            mappedBy = "element",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Isotope> isotopes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Element)) return false;
        Element element = (Element) o;
        return number == element.number &&
                Objects.equals(id, element.id) &&
                Objects.equals(name, element.name) &&
                Objects.equals(symbol, element.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, symbol, number);
    }

    @Override
    public String toString() {
        return "Element{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", number=" + number +
                ", mass=" + mass +
                '}';
    }
}
