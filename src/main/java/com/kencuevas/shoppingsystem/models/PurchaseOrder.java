package com.kencuevas.shoppingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

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
@Table(name = "PurcharseOrderTB")
public class PurchaseOrder {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;
    private LocalDate dateOrder;
    private boolean status;
    private int quantity;
    private BigInteger unitCost;
    private int idAsientoContable;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

}
