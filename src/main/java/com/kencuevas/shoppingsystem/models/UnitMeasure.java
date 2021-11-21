package com.kencuevas.shoppingsystem.models;
import com.kencuevas.shoppingsystem.models.Article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Kenny Cuevas
 * @version 1.0.0
 * @since 1.0
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "unit_measure")
public class UnitMeasure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private boolean status;
    @OneToOne(mappedBy = "unitMeasure")
    private Article article;

    public UnitMeasure() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * gets id
     *
     * @return the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * gets description
     *
     * @return the description of a Unit measure.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets description.
     *
     * @param description of unit measuret
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * gets status
     *
     * @return the status of a unit measure (false or true)
     */
    public boolean isStatus() {
        return status;
    }
    /**
     * Sets status.
     *
     * @param status of unit measuret, set of false or true
     */
    public void setStatus(boolean status) {
        this.status = status;
    }
}
