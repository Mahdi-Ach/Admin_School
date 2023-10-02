package com.example.dashboardrest.GlobalController.ControllerPersion;

import com.example.dashboardrest.Services.ServicePerson.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Person")
public class PersonContoller {
    @Autowired
    private PersonService personService;
    @DeleteMapping("/deletePerson")
    public ResponseEntity<HashMap<String,String>> DeletePerson(@RequestParam("List_id") List<Long> id){
        personService.deletePerson(id);
        HashMap<String,String> msg_deleted = new HashMap<>();
        msg_deleted.put("msg_deleted","Teacher are deleted suceffuly");
        return ResponseEntity.ok(msg_deleted);
    }
}
