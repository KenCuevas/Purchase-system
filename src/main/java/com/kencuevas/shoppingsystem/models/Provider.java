package com.kencuevas.shoppingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "ProvidersTB")
public class Provider {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "cedulas", nullable = false)
    private String cedula;
    @Column(name = "Trade_name", nullable = false)
    private String TradeName;
    @Column(name = "status", nullable = false)
    private boolean Status;
}
