package com.kencuevas.shoppingsystem.dto;

import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

@Data
public class PurchaseOrderDTO {
    private long id;
    private String orderNumber;
    private LocalDate dateOrder;
    private boolean status;
    private int quantity;
    private BigInteger unitCost;
    private int idAsientoContable;
    private Set<ArticleDTO> articles;
}
