package com.example.dashboardrest.Services.ServicePerson;

import com.example.dashboardrest.BO.Members.GenderPerson;
import com.example.dashboardrest.DAO.PersonDAO.PersonRepository;
import com.example.dashboardrest.DAO.PersonDAO.StudentRepository;
import com.example.dashboardrest.BO.Members.Student;
import com.example.dashboardrest.DTOs.School_tools.ResponseNoteDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.GlobalException.validation_studentinfo;
import com.example.dashboardrest.Projection.StudentProjection;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private StudentRepository studentRepository;
    public HashMap<String,String> saveStudent(Student stu, MultipartFile file){
        HashMap<String,String> error_validation = new HashMap<>();
        if (file.isEmpty()) {
            error_validation.put("file_erreur","Please select a file to upload");
        }else if(file.getSize()<10 || file.getSize()>200000){
            error_validation.put("file_size","the file should be min : 10 and max : 300000");
        }
        if (!isValidGender(stu.getGender())) {
            error_validation.put("gender","gender must be Male or Female");
        }
        if(personRepository.existsPersonsByEmail(stu.getEmail())){
            error_validation.put("email_unicity","email should be unique");
        }
        if(personRepository.existsPersonsByPhone(stu.getPhone())){
            error_validation.put("phone_unicity","phone should be unique");
        }
        if(!error_validation.isEmpty()){
            throw new validation_studentinfo(error_validation);
        }
        UUID unique = UUID.randomUUID();
        try {
            String fileName = file.getOriginalFilename();
            String[] extension = fileName.split("\\.");
            String new_filename = unique+"."+extension[1];
            String filePath = "C:\\Users\\achba\\Desktop\\DashboardFinal\\DashBoardRest\\src\\main\\resources\\images\\" + new_filename;
            File dest = new File(filePath);
            file.transferTo(dest);
            stu.setFilename(new_filename);
        } catch (IOException e) {
            error_validation.put("file_erreur","Failed to upload file");
        }
        studentRepository.save(stu);
        HashMap<String,String> success_message = new HashMap<String,String>();
        success_message.put("success","student is created succefuly");
        return success_message;
    }
    public List<StudentProjection> listStudent(){
        return studentRepository.findAllBy();
    }
    public Student getStudentInfos(Long Student_id) throws NoUserFound, IOException {
        Student stu = getStudentById(Student_id);
        if(stu == null){
            throw new NoUserFound("No User Found");
        }
        BufferedImage bImage = ImageIO.read(new File("C:\\Users\\achba\\Desktop\\DashboardFinal\\DashBoardRest\\src\\main\\resources\\images\\"+stu.getFilename()));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bImage, "jpg", bos );
        byte [] data = bos.toByteArray();
        stu.setImagebyte(data);

        return stu;
    }
    public List<ResponseNoteDto> getList_Notes(String filiernom){
        List<ResponseNoteDto> std = studentRepository.findAll().stream()
                .map(student -> {
                    if(student.getFilier().getFilierNom().equals(filiernom)){
                        ResponseNoteDto stu = new ResponseNoteDto();
                        stu.setStudentnom(student.getNom());

                        Map<String, String> note = student.getNotesList().stream()
                                .collect(Collectors.toMap(
                                        notes -> notes.getModule().getModuleNom(),
                                        notes -> notes.getNote()
                                ));

                        stu.setModule_note(note);
                        return stu;
                    }
                    return null;
                })
                .collect(Collectors.toList());
        return std;
    }
    public Student getStudentById(Long Student_id){
        return studentRepository.getStudentById(Student_id);
    }
    public HashMap<String,String>   UpdateStudentData(MultipartFile file,Long id){
        HashMap<String,String> error_validation = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if (file.isEmpty()) {
            error_validation.put("file_erreur","Please select a file to upload");
            throw new validation_studentinfo(error_validation);
        }
        if(file.getSize()<10 || file.getSize()>200000){
            error_validation.put("file_size","the file should be min : 10 and max : 300000");
            throw new validation_studentinfo(error_validation);
        }
        UUID unique = UUID.randomUUID();
        try {
            String file_old_image = "C:\\Users\\achba\\Desktop\\DashboardFinal\\DashBoardRest\\src\\main\\resources\\images\\"+student.getFilename();
            File file_to_remove = new File(file_old_image);
            System.out.println(file_old_image);
            if (file_to_remove.exists()) {
                if (file_to_remove.delete()) {
                    System.out.println("File deleted successfully: " + file_old_image);
                } else {
                    System.err.println("Failed to delete the file: " + file_old_image);
                }
            } else {
                System.err.println("File does not exist: " + file_old_image);
            }
            String fileName = file.getOriginalFilename();
            String[] extension = fileName.split("\\.");
            String fullName = unique+"."+extension[1];
            String filePath = "C:\\Users\\achba\\Desktop\\DashboardFinal\\DashBoardRest\\src\\main\\resources\\images\\" + fullName;
            File dest = new File(filePath);
            file.transferTo(dest);
            student.setFilename(fullName);
        } catch (IOException e) {
            error_validation.put("file_erreur","Failed to upload file");
        }
        if(!error_validation.isEmpty()){
            throw new validation_studentinfo(error_validation);
        }
        studentRepository.save(student);
        HashMap<String,String> success_message = new HashMap<String,String>();
        success_message.put("success","student is created succefuly");
        return error_validation;
    }
    private boolean isValidGender(String gender) {
        return GenderPerson.Male.name().equalsIgnoreCase(gender) || GenderPerson.Female.name().equalsIgnoreCase(gender);
    }
    public HashMap<String,String> Update_Cne(String cne,Long id) throws NoUserFound, ConstraintViolationException {
        HashMap<String,String> Cne_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student.getCne().equals(cne)){
            return null;
        }
        if(student == null){
            throw new NoUserFound("No User Found");
        }
        if(studentRepository.existsStudentByCne(cne) && !student.getCne().equals(cne)){
            Cne_msg.put("Cne_unicity","Cne is Already Exist");
            throw new validation_studentinfo(Cne_msg);
        }
        student.setCne(cne);
        studentRepository.save(student);
        Cne_msg.put("cne","Cne is updated successfuly");
        return Cne_msg;
    }
    public HashMap<String,String> Update_Nom(String Nom,Long id) throws NoUserFound {
        HashMap<String,String> Nom_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            throw new NoUserFound("No User Found");
        }
        student.setNom(Nom);
        studentRepository.save(student);
        Nom_msg.put("Nom","Nom is updated successfuly");
        return Nom_msg;
    }
    public HashMap<String,String> Update_Prenom(String Prenom,Long id) throws NoUserFound {
        HashMap<String,String> Prenom_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            throw new NoUserFound("No User Found");
        }
        student.setPrenom(Prenom);
        studentRepository.save(student);
        Prenom_msg.put("Prenom","Prenom is updated successfuly");
        return Prenom_msg;
    }
    public HashMap<String,String> Update_Address(String Address,Long id) throws NoUserFound {
        HashMap<String,String> Cne_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            Cne_msg.put("no_user","No User Found");
            Cne_msg.put("address",student.getAddress());
            throw new validation_studentinfo(Cne_msg);
        }
        student.setAddress(Address);
        studentRepository.save(student);
        Cne_msg.put("Address","Address is updated successfuly");
        return Cne_msg;
    }
    public HashMap<String,String> update_Email(String Email,Long id) throws NoUserFound {
        HashMap<String,String> Email_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            throw new NoUserFound("No User Found");
        }else if(Email.equals(student.getEmail())){
            return null;
        }
        if(personRepository.existsPersonsByEmail(Email)){
            Email_msg.put("Email_exist","Email already exist");
            throw new validation_studentinfo(Email_msg);
        }
        student.setEmail(Email);
        studentRepository.save(student);
        Email_msg.put("Email","Email is updated successfuly");
        return Email_msg;
    }
    public HashMap<String,String> Update_Phone(String Phone,Long id) throws NoUserFound {
        HashMap<String,String> Phone_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            throw new NoUserFound("No User Found");
        }else if(Phone.equals(student.getPhone())){
            return null;
        }
        if(personRepository.existsPersonsByPhone(Phone)){
            Phone_msg.put("Phone","Phone already exist");
            throw new validation_studentinfo(Phone_msg);
        }
        student.setPhone(Phone);
        studentRepository.save(student);
        Phone_msg.put("Phone","Phone is updated successfuly");
        return Phone_msg;
    }
    public HashMap<String,String> Update_gender(String gender,Long id){
        HashMap<String,String> Gender_msg = new HashMap<>();
        if (!isValidGender(gender)) {
            Gender_msg.put("gender","gender must be Male or Female");
            throw new validation_studentinfo(Gender_msg);
        }
        Student student = studentRepository.getStudentById(id);
        student.setGender(gender);
        studentRepository.save(student);
        Gender_msg.put("gender","Gender is updated successfuly");
        return Gender_msg;
    }
    public HashMap<String,String> Update_Birthday(String birthday,Long id) throws NoUserFound {
        HashMap<String,String> Birthday_msg = new HashMap<>();
        Student student = studentRepository.getStudentById(id);
        if(student == null){
            throw new NoUserFound("No User Found");
        }else if(birthday.equals(student.getBirthday())){
            return null;
        }

        student.setBirthday(birthday);
        studentRepository.save(student);
        Birthday_msg.put("Birthday","Birthday is updated successfuly");
        return Birthday_msg;
    }
}
