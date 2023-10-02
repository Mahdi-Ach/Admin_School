package com.example.dashboardrest.DTOs.School_tools;

import com.example.dashboardrest.BO.Members.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseNoteDto {
    private String studentnom;
    private Map<String,String> module_note;
    public ResponseNoteDto(){}
    public ResponseNoteDto(String studentnom, Map<String, String> module_note) {
        this.studentnom = studentnom;
        this.module_note = module_note;
    }

    public String getStudentnom() {
        return studentnom;
    }

    public void setStudentnom(String studentnom) {
        this.studentnom = studentnom;
    }

    public Map<String, String> getModule_note() {
        return module_note;
    }

    public void setModule_note(Map<String, String> module_note) {
        this.module_note = module_note;
    }
}
