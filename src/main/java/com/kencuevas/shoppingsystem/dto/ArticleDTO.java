package com.kencuevas.shoppingsystem.dto;
import lombok.Data;

import java.util.Set;

@Data
public class ArticleDTO {
    private long id;
    private String description;
    private String brand;
    private int availability;
    private boolean status;
    private UnitMeasureDTO measures;
}
