package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.Projection.List_module_nom;

import java.util.List;

public class ElementsListDto {
    private Long id;
    private String elementNom;
    private Modules modules;

    public ElementsListDto(Long id, String elementNom, Modules modules) {
        this.id = id;
        this.elementNom = elementNom;
        this.modules = modules;
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

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
}
