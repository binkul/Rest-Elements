package com.chemistry.elements.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "isotopes")
public class Isotope {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int massNumber;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "element_id", nullable = false)
    private Element element;

    @Override
    public String toString() {
        return "Isotope{" +
                "id=" + id +
                ", massNumber=" + massNumber +
                ", element=" + element +
                '}';
    }
}
