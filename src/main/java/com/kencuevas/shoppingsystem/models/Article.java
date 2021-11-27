package com.kencuevas.shoppingsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Data
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unitMeasureId", nullable = false)
    private UnitMeasure unitMeasure;
    private int availability;
    private boolean status;

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
