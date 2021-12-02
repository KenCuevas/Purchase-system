package com.kencuevas.shoppingsystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;
@ApiModel(description = "Purchase order model information")
@Data
public class PurchaseOrderDTO {
    @ApiModelProperty(value = "Orden ID")
    private long id;
    @ApiModelProperty(value = "Order number of the purchase")
    private String orderNumber;
    @ApiModelProperty(value = "sale date of purchase")
    private LocalDate dateOrder;
    @ApiModelProperty(value = "Purchase order status")
    private boolean status;
    @ApiModelProperty(value = "Quantity of items purchased on the purchase order")
    private int quantity;
    @ApiModelProperty(value = "Unit cost of purchase")
    private BigInteger unitCost;
    @ApiModelProperty(value = "Articles order")
    private Set<ArticleDTO> article;
}
