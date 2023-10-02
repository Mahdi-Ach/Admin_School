package com.example.dashboardrest.DTOs.School_tools;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class FilierListDto {
    private Long id;
    private String filierNom;

    public FilierListDto(Long id, String filierNom) {
        this.id = id;
        this.filierNom = filierNom;
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
}
