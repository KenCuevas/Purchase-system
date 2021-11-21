package com.kencuevas.shoppingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "cedulas", nullable = false)
    private String cedula;
    @Column(name = "Trade_name", nullable = false)
    private String TradeName;
    @Column(name = "status", nullable = false)
    private boolean Status;

    public Provider() {
    }
    /**
     * gets id
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets id.
     *
     * @param id the last name
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * gets cedula
     *
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }
    /**
     * Sets cedula.
     *
     * @param cedula the last name
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    /**
     * gets trade name
     *
     * @return the trade name
     */
    public String getTradeName() {
        return TradeName;
    }
    /**
     * Sets trade name.
     *
     * @param tradeName the last name
     */
    public void setTradeName(String tradeName) {
        TradeName = tradeName;
    }
    /**
     * gets status
     *
     * @return the status
     */
    public boolean isStatus() {
        return Status;
    }
    /**
     * Sets status.
     *
     * @param status the last name
     */
    public void setStatus(boolean status) {
        Status = status;
    }
}
