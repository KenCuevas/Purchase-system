package com.kencuevas.shoppingsystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@ApiModel(description = "Provider model information")
@Data
public class ProviderDTO {
    @ApiModelProperty(value = "Provider ID")
    private Long id;
    @ApiModelProperty(value = "Supplier Identification Card")
    private String cedula;
    @ApiModelProperty(value = "Supplier's trade name")
    @NotEmpty
    @Size(min = 8, message = "The provider name must be at least 8 characters long")
    private String TradeName;
    @ApiModelProperty(value = "Set the status of the provider, whether it is active or no")
    private boolean Status;
}
