package com.kencuevas.shoppingsystem.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class DepartmentDTO {
    private long id;
    private String name;
    private boolean status;
}
