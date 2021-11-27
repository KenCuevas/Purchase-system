package com.kencuevas.shoppingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */

@Entity
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "names", nullable = false, length = 100)
    private String name;
    @Column(name = "status", nullable = false)
    private boolean status;

    public Department() {
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
     * gets name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets status
     *
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the last name
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
