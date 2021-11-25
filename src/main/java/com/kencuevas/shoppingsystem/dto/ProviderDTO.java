package com.kencuevas.shoppingsystem.dto;

import lombok.Data;
/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Data
public class ProviderDTO {
    private Long id;
    private String cedula;
    private String TradeName;
    private boolean Status;
}
