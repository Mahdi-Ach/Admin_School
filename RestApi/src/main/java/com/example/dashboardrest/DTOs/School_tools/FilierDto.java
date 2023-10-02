package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.School_tools.Class_room;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilierDto {
    private Long id;
    private String filierNom;
    private List<Class_room> classes = new ArrayList<>();

    public FilierDto(Long id, String filierNom, List<Class_room> classes) {
        this.id = id;
        this.filierNom = filierNom;
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilierNom() {
        return filierNom;
    }

    public void setFilierNom(String filierNom) {
        this.filierNom = filierNom;
    }

    public List<Class_room> getClasses() {
        return classes;
    }

    public void setClasses(List<Class_room> classes) {
        this.classes = classes;
    }
}
