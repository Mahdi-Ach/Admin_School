package com.example.dashboardrest.GlobalController.ControllerPersion;

import com.example.dashboardrest.BO.Members.Student;
import com.example.dashboardrest.DAO.PersonDAO.StudentRepository;
import com.example.dashboardrest.DTOs.School_tools.ResponseNoteDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.Projection.StudentProjection;
import com.example.dashboardrest.Services.ServicePerson.StudentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Student")
@Validated
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/create_student")
    public ResponseEntity<HashMap<String,String>> Create_Student(@Valid @ModelAttribute Student s, @RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(studentService.saveStudent(s,file));
    }
    @GetMapping("/list_student")
    public ResponseEntity<List<StudentProjection>> getList_Student(){
        return ResponseEntity.ok(studentService.listStudent());
    }
    @GetMapping("/list_notes")
    public ResponseEntity<List<ResponseNoteDto>> getList_Studenet(
            @RequestParam(defaultValue = "Geni Info")
            String filiernom
    ){

        return ResponseEntity.ok(studentService.getList_Notes(filiernom));
    }
    @GetMapping("/Detail_student")
    public ResponseEntity<Student> getDetailStudent(@RequestParam("Student_id") Long Student_id) throws NoUserFound, IOException {
        return ResponseEntity.ok(studentService.getStudentInfos(Student_id));
    }
    @PutMapping("/update_Student/image/{id}")
    public ResponseEntity<HashMap<String,String>> updateImage(
            @ModelAttribute MultipartFile file,
            @PathVariable("id") Long id
    ){
        System.out.println(file);
        return  ResponseEntity.ok(studentService.UpdateStudentData(file,id));
    }
    @PutMapping("/update_Student/cne/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateCne(
            @RequestParam(required = false)
            @NotNull(message = "Cne is required")
            @NotEmpty(message = "Cne is required")
            String cne,
            @PathVariable Long id) throws NoUserFound {
        HashMap<String,String> aa = new HashMap<>();
        aa.put("dsq","dsq");
        return ResponseEntity.ok(studentService.Update_Cne(cne,id));
    }
    @GetMapping("/t")
    public ResponseEntity<String> a(@RequestParam("e") @Size(min = 3)  String e){
        System.out.println(e);
        return ResponseEntity.ok("dsqdsq");
    }
    @PutMapping("/update_Student/nom/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateNom(
            @RequestParam(required = false)
            @NotNull(message = "Nom is required")
            @NotEmpty(message = "Nom is required")
            String nom,@PathVariable Long id
    ) throws NoUserFound {
        return ResponseEntity.ok(studentService.Update_Nom(nom,id));
    }
    @PutMapping("/update_Student/prenom/{id}")
    public ResponseEntity<HashMap<String,String>> UpdatePrenom(
            @RequestParam(required = false)
            @NotNull(message = "Prenom is required")
            @NotEmpty(message = "Prenom is required")
            String prenom,
            @PathVariable Long id
    ) throws NoUserFound {
        return ResponseEntity.ok(studentService.Update_Prenom(prenom,id));
    }
    @PutMapping("/update_Student/email/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateEmail(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Email is required")
            @NotEmpty(message = "Email is required")
            @Email(message = "Email should be valid")
            String email) throws NoUserFound {
        HashMap<String,String> response = studentService.update_Email(email,id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update_Student/phone/{id}")
    public ResponseEntity<HashMap<String,String>> UpdatePhone(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Phone is required")
            @NotEmpty(message = "Cne is required")
            @Pattern(regexp = "^$|\\d{2}-\\d{2}-\\d{2}-\\d{2}-\\d{2}$",message = "Phone pattern should match XX-XX-XX-XX-XX")
            String phone
    ) throws NoUserFound {
        return ResponseEntity.ok(studentService.Update_Phone(phone,id));
    }
    @PutMapping("/update_Student/address/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateAddress(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Address is required")
            @NotEmpty(message = "Address is required")
            String address
    ) throws NoUserFound {
        return ResponseEntity.ok(studentService.Update_Address(address,id));
    }
    @PutMapping("/update_Student/gender/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateGender(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Gender is required")
            @NotEmpty(message = "Gender is required")
            String gender
    ) throws NoUserFound {
        return ResponseEntity.ok(studentService.Update_gender(gender,id));
    }
    @PutMapping("/update_Student/birthday/{id}")
    public ResponseEntity<HashMap<String,String>> UpdateBirthday(
            @PathVariable Long id,
            @RequestParam(required = false)
            @NotNull(message = "Birthday is required")
            @NotEmpty(message = "Birthday is required")
            @Pattern(regexp = "^$|\\d{4}-\\d{2}-\\d{2}$",message = "Birthday pattern should match XXXX-XX-XX")
            String birthday
    ) throws NoUserFound {
        return ResponseEntity.ok(studentService.Update_Birthday(birthday,id));
    }
}
