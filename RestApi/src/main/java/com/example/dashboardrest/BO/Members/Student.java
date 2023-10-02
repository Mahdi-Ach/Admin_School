package com.example.dashboardrest.BO.Members;

import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.BO.School_tools.Notes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Student extends Persons{
    @NotEmpty
    private String cne;
    @OneToMany
    private List<Notes> notesList;
    @ManyToOne
    private Filier filier;

    public Filier getFilier() {
        return filier;
    }

    public void setFilier(Filier filier) {
        this.filier = filier;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }
    public Student() {
    }

    public Student(String cne, String nom, String prenom) {
        this.cne = cne;
    }
}
