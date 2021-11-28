package com.kencuevas.shoppingsystem.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class DepartmentDTO {
    private long id;
    @NotEmpty
    @Size(min=5, message = "The department name must be at least 5 characters long")
    private String name;
    private boolean status;
}
