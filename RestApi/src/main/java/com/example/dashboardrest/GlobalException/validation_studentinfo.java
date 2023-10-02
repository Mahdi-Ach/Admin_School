package com.example.dashboardrest.GlobalException;

import java.util.HashMap;

public class validation_studentinfo extends RuntimeException{
    private HashMap<String,String> field_const;
    public validation_studentinfo(HashMap<String, String> messages){
        this.field_const = messages;
    }
    public HashMap<String, String> getDetails() {
        return field_const;
    }
}
