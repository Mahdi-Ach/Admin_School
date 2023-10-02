package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.Projection.List_class_nom;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ModulesDto {
    private Long id;
    private String nomModule;
    private String codeModule;
    private String classRoom;
    private List<List_class_nom> listClassNoms;

    public ModulesDto(Long id, String nomModule, String codeModule, String classRoom, List<List_class_nom> listClassNoms) {
        this.id = id;
        this.nomModule = nomModule;
        this.codeModule = codeModule;
        this.classRoom = classRoom;
        this.listClassNoms = listClassNoms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public List<List_class_nom> getListClassNoms() {
        return listClassNoms;
    }

    public void setListClassNoms(List<List_class_nom> listClassNoms) {
        this.listClassNoms = listClassNoms;
    }
}
