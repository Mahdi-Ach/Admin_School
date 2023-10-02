package com.example.dashboardrest.BO.School_tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
public class Elements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "elementnom",unique = true)
    @NotEmpty(message = "nom d'element is required")
    private String elementNom;

    private Date deletetime;
    @ManyToOne
    @JoinColumn(name = "modules_id")
    @JsonIgnore
    private Modules modules;

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getElementNom() {
        return elementNom;
    }

    public void setElementNom(String elementNom) {
        this.elementNom = elementNom;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Elements{" +
                "id=" + id +
                ", elementNom='" + elementNom + '\'' +
                ", deletetime=" + deletetime +
                '}';
    }
}
