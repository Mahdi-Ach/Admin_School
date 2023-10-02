package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.School_tools.Filier;

public class ClassListDto {
    private Long id;
    private String classNom;
    private Filier filier;

    public ClassListDto(Long id, String classNom, Filier filier) {
        this.id = id;
        this.classNom = classNom;
        this.filier = filier;
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

    public Filier getFilier() {
        return filier;
    }

    public void setFilier(Filier filier) {
        this.filier = filier;
    }
}
