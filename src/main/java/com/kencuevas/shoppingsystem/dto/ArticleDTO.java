package com.kencuevas.shoppingsystem.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import java.util.Set;

@ApiModel(description = "Article model information")
@Data
public class ArticleDTO {
    @ApiModelProperty(value = "Article ID")
    private long id;
    @ApiModelProperty(value = "Item description")
    private String description;
    @ApiModelProperty(value = "Brand owner or distributor of the item.")
    private String brand;
    @ApiModelProperty(value = "Unit quantity in stock")
    private int availability;
    @ApiModelProperty(value = "Item availability")
    private boolean status;
    @ApiModelProperty(value = "Unit measure articles")
    private UnitMeasureDTO measures;
}
