package com.example.dashboardrest.BO.School_tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Modules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "module_nom")
    @NotBlank(message = "nom de module is required")
    @NotEmpty(message = "nom du module is required")
    private String moduleNom;
    @Column(name = "module_code")
    @NotBlank(message = "code de module is required")
    private String codeModule;
    private Date deletetime;
    @OneToMany(mappedBy = "modules",cascade = CascadeType.ALL)
    @Where(clause = "deletetime is null")
    @JsonIgnore
    private List<Elements> elements = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonIgnore
    private Class_room classes;
    @OneToMany
    @JsonIgnore
    private List<Notes> notesList;

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }

    public Class_room getClasses() {
        return classes;
    }

    public void setClasses(Class_room classes) {
        this.classes = classes;
    }

    public Long getId() {
        return id;
    }

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCodeModule() {
        return codeModule;
    }

    public void setCodeModule(String codeModule) {
        this.codeModule = codeModule;
    }

    public List<Elements> getElements() {
        return elements;
    }

    public void setElements(List<Elements> elements) {
        this.elements = elements;
    }

    public String getModuleNom() {
        return moduleNom;
    }

    public void setModuleNom(String moduleNom) {
        this.moduleNom = moduleNom;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "id=" + id +
                ", nomModule='" + moduleNom + '\'' +
                ", codeModule='" + codeModule + '\'' +
                ", deletetime=" + deletetime +
                '}';
    }
}
