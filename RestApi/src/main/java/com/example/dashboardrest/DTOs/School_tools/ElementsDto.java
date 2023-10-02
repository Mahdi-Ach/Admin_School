package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.Projection.List_module_nom;

import java.util.List;

public class ElementsDto {
    private Long id;
    private String elementNom;
    private String moduleNom;
    private List<List_module_nom> listModuleNoms;

    public ElementsDto(Long id, String elementNom, String Nommodules, List<List_module_nom> listModuleNoms) {
        this.id = id;
        this.elementNom = elementNom;
        this.moduleNom = Nommodules;
        this.listModuleNoms = listModuleNoms;
    }

    public List<List_module_nom> getListModuleNoms() {
        return listModuleNoms;
    }

    public void setListModuleNoms(List<List_module_nom> listModuleNoms) {
        this.listModuleNoms = listModuleNoms;
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

    public String getModuleNom() {
        return moduleNom;
    }

    public void setModuleNom(String moduleNom) {
        this.moduleNom = moduleNom;
    }
}
