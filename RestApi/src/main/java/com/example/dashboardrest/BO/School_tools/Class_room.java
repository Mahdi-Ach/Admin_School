package com.example.dashboardrest.BO.School_tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Class_room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nom d'element is required")
    @NotEmpty(message = "nom d'element is required")
    @Column(name = "class_nom")
    private String classNom;
    @OneToMany(mappedBy = "classes",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Modules> modules = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "filier_id")
    @JsonIgnore
    private Filier filier;

    public Filier getFilier() {
        return filier;
    }

    public void setFilier(Filier filier) {
        this.filier = filier;
    }

    private Date deletetime;


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

    public String getClassNom() {
        return classNom;
    }

    public void setClassNom(String classNom) {
        this.classNom = classNom;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Class_room{" +
                "id=" + id +
                ", classNom='" + classNom + '\'' +
                ", deletetime=" + deletetime +
                '}';
    }
}
