package com.example.dashboardrest.BO.School_tools;

import com.example.dashboardrest.BO.Members.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Filier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nom de filier is required")
    @NotEmpty(message = "nom de filier is required")
    @Column(name = "filier_nom")
    private String filierNom;
    @OneToMany
    private List<Student> students;
    private Date deletetime;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @OneToMany(mappedBy = "filier",cascade = CascadeType.ALL)
    @Where(clause = "deletetime is null")
    @JsonIgnore
    private List<Class_room> classes = new ArrayList<>();

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

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public List<Class_room> getClasses() {
        return classes;
    }

    public void setClasses(List<Class_room> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Filier{" +
                "id=" + id +
                ", filierNom='" + filierNom + '\'' +
                ", deletetime=" + deletetime +
                '}';
    }
}
