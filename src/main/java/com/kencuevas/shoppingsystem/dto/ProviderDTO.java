package com.kencuevas.shoppingsystem.dto;

import lombok.Data;

@Data
public class ProviderDTO {
    private Long id;
    private String cedula;
    private String TradeName;
    private boolean Status;
}
