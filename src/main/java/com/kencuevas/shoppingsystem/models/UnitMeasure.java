package com.kencuevas.shoppingsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is an entity that allows us to mirror the unit_measure_TB table stored in the database.
 * @author Kenny Cuevas
 * @version 25/10/2021/A
 */

//Lombok library annotations that allow us to reduce the amount of code we create.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "MeasureTB")
public class UnitMeasure{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    @Column(
            name = "measure_id")
    private Long id;
    private String description;
    private boolean status;
    @OneToOne(mappedBy = "measure", cascade = CascadeType.ALL)
    private Article article;

}
