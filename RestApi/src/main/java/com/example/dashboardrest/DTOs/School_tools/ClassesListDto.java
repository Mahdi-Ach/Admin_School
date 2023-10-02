package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.Projection.List_filier_nom;
import com.example.dashboardrest.Projection.List_module_nom;

import java.util.List;

public class ClassesListDto {
    private Long id;
    private String classNom;
    private String nomfilier;
    private List<List_filier_nom> listFilierNoms;

    public ClassesListDto(Long id, String classNom, String nomfilier, List<List_filier_nom> listFilierNoms) {
        this.id = id;
        this.classNom = classNom;
        this.nomfilier = nomfilier;
        this.listFilierNoms = listFilierNoms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassNom() {
        return classNom;
    }

    public void setClassNom(String classNom) {
        this.classNom = classNom;
    }

    public String getNomfilier() {
        return nomfilier;
    }

    public void setNomfilier(String nomfilier) {
        this.nomfilier = nomfilier;
    }

    public List<List_filier_nom> getListFilierNoms() {
        return listFilierNoms;
    }

    public void setListFilierNoms(List<List_filier_nom> listFilierNoms) {
        this.listFilierNoms = listFilierNoms;
    }
}
