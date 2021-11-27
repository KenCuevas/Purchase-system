package com.kencuevas.shoppingsystem.dto;
import lombok.Data;

@Data
public class ArticleDTO {
    private long id;
    private String description;
    private String brand;
    private int availability;
    private boolean status;
}
