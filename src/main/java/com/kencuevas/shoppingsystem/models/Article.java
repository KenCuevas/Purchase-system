package com.kencuevas.shoppingsystem.models;

import lombok.*;

import javax.persistence.*;
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

    //A 1 to 1 relationship is created between the TB units of measure and TB articles.
    @OneToOne
    @JoinColumn(name = "measure_id", updatable = false, nullable = false)
    private UnitMeasure measure;


//    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY,
//    cascade = CascadeType.ALL)
//    private Set<PurchaseOrder> purchaseOrderSet;


//    public Set<PurchaseOrder> getPurchaseOrderSet() {
//        return purchaseOrderSet;
//    }
//
//    public void setPurchaseOrderSet(Set<PurchaseOrder> purchaseOrderSet) {
//        this.purchaseOrderSet = purchaseOrderSet;
//    }

}
