package com.example.dashboardrest.GlobalController.ControllerPersion;

import com.example.dashboardrest.BO.Members.Teacher;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.Projection.TeacherProjection;
import com.example.dashboardrest.Services.ServicePerson.TeacherService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Teacher")
@Validated
public class TeacherController {
    private final TeacherService teacherservice;

    @Autowired
    public TeacherController(TeacherService teacherservice) {
        this.teacherservice = teacherservice;
    }

    @PostMapping(value = "/create_teacher")
    public ResponseEntity<HashMap<String,String>> Create_teacher(@Valid @ModelAttribute Teacher s, @RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(teacherservice.saveTeacher(s,file));
    }
    @GetMapping("/list_teacher")
    public ResponseEntity<List<TeacherProjection>> getList_teacher(){
        return ResponseEntity.ok(teacherservice.listTeacher());
    }
    @GetMapping("/Detail_teacher")
    public ResponseEntity<Teacher> getDetailteacher(@RequestParam("Teacher_id") Long Teacher_id) throws NoUserFound, IOException {
        return ResponseEntity.ok(teacherservice.getTeacherInfos(Teacher_id));
    }
    @PutMapping("/update_teacher/image/{id}")
    public ResponseEntity<HashMap<String,String>> updateImage(
            @ModelAttribute MultipartFile file,
            @PathVariable("id") Long id
    ){
        System.out.println(file);
        return  ResponseEntity.ok(teacherservice.UpdateTeacherData(file,id));
    }
    @PutMapping("/update_teacher/cin/{id}")
    public ResponseEntity<HashMap<String,String>> Updatecin(
            @RequestParam(required = false)
            @NotNull(message = "cin is required")
            @NotEmpty(message = "cin is required")
            String cin,
            @PathVariable Long id) throws NoUserFound {
        HashMap<String,String> aa = new HashMap<>();
        aa.put("dsq","dsq");
        return ResponseEntity.ok(teacherservice.Update_Cin(cin,id));
    }
    @GetMapping("/t")
    public ResponseEntity<String> a(@RequestParam("e") @Size(min = 3)  String e){
        System.out.println(e);
        return ResponseEntity.ok("dsqdsq");
    }
    @PutMapping("/update_teacher/nom/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateNom(
            @RequestParam(required = false)
            @NotNull(message = "Nom is required")
            @NotEmpty(message = "Nom is required")
            String nom,@PathVariable Long id
    ) throws NoUserFound {
        return ResponseEntity.ok(teacherservice.Update_Nom(nom,id));
    }
    @PutMapping("/update_teacher/prenom/{id}")
    public ResponseEntity<HashMap<String,String>> UpdatePrenom(
            @RequestParam(required = false)
            @NotNull(message = "Prenom is required")
            @NotEmpty(message = "Prenom is required")
            String prenom,
            @PathVariable Long id
    ) throws NoUserFound {
        return ResponseEntity.ok(teacherservice.Update_Prenom(prenom,id));
    }
    @PutMapping("/update_teacher/email/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateEmail(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Email is required")
            @NotEmpty(message = "Email is required")
            @Email(message = "Email should be valid")
            String email) throws NoUserFound {
        HashMap<String,String> response = teacherservice.update_Email(email,id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update_teacher/phone/{id}")
    public ResponseEntity<HashMap<String,String>> UpdatePhone(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Phone is required")
            @NotEmpty(message = "cin is required")
            @Pattern(regexp = "^$|\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}$",message = "Phone pattern should match XX-XX-XX-XX-XX")
            String phone
    ) throws NoUserFound {
        return ResponseEntity.ok(teacherservice.Update_Phone(phone,id));
    }
    @PutMapping("/update_teacher/address/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateAddress(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Address is required")
            @NotEmpty(message = "Address is required")
            String address
    ) throws NoUserFound {
        return ResponseEntity.ok(teacherservice.Update_Address(address,id));
    }
    @PutMapping("/update_teacher/gender/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateGender(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Gender is required")
            @NotEmpty(message = "Gender is required")
            String gender
    ) throws NoUserFound {
        return ResponseEntity.ok(teacherservice.Update_gender(gender,id));
    }
    @PutMapping("/update_teacher/birthday/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateBirthday(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Birthday is required")
            @NotEmpty(message = "Birthday is required")
            @Pattern(regexp = "^$|\\d{4}-\\d{2}-\\d{2}$",message = "Birthday pattern should match XXXX-XX-XX")
            String birthday
    ) throws NoUserFound {
        return ResponseEntity.ok(teacherservice.Update_Birthday(birthday,id));
    }

}
