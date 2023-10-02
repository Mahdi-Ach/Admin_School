package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.School_tools.Class_room;

public class ModuleListDto {
    private Long id;
    private String nomModule;
    private String codeModule;
    private Class_room classRoom;

    public ModuleListDto(Long id, String nomModule, String codeModule, Class_room classRoom) {
        this.id = id;
        this.nomModule = nomModule;
        this.codeModule = codeModule;
        this.classRoom = classRoom;
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

    public Class_room getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(Class_room classRoom) {
        this.classRoom = classRoom;
    }
}
