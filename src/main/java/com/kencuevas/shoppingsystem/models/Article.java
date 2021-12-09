package com.kencuevas.shoppingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(
        name = "ArticlesTB")
public class Article{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String brand;
    private int availability;
    private boolean status;

    //A 1 to n relationship is created between the TB units of measure and TB articles.
//    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UnitMeasure> measures = new HashSet<>();
    @OneToOne
    @JoinColumn(name = "measure_id", updatable = false, nullable = false)
    private UnitMeasure measure;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<PurchaseOrder> purchaseOrderSet;

}