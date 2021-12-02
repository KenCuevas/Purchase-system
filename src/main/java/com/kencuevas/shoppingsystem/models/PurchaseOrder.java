package com.kencuevas.shoppingsystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

}
