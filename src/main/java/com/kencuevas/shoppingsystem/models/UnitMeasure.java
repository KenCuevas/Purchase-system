package com.kencuevas.shoppingsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "unitMeasureTB")
public class UnitMeasure{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private boolean status;
    @OneToMany(mappedBy = "unitMeasure", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Article> articles = new HashSet<>();

}
