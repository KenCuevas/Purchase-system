package com.kencuevas.shoppingsystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Department model information")
@Data
public class DepartmentDTO {
    @ApiModelProperty(value = "Department ID")
    private long id;
    @ApiModelProperty(value = "Department name")
    @NotEmpty
    @Size(min=5, message = "The department name must be at least 5 characters long")
    private String name;
    @ApiModelProperty(value = "set the status of the department, whether it is active or not")
    private boolean status;
}
