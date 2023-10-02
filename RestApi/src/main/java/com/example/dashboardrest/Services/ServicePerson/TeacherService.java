package com.example.dashboardrest.Services.ServicePerson;

import com.example.dashboardrest.BO.Members.GenderPerson;
import com.example.dashboardrest.BO.Members.Teacher;
import com.example.dashboardrest.DAO.PersonDAO.PersonRepository;
import com.example.dashboardrest.DAO.PersonDAO.TeacherRepository;
import com.example.dashboardrest.BO.Members.Teacher;
import com.example.dashboardrest.GlobalException.InvalidGender;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.GlobalException.validation_studentinfo;
import com.example.dashboardrest.Projection.TeacherProjection;
import com.example.dashboardrest.Projection.TeacherProjection;
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
import java.util.UUID;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    public HashMap<String,String> saveTeacher(Teacher stu, MultipartFile file){
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
        teacherRepository.save(stu);
        HashMap<String,String> success_message = new HashMap<String,String>();
        success_message.put("success","Teacher is created succefuly");
        return success_message;
    }
    public List<TeacherProjection> listTeacher(){
        return teacherRepository.findAllProjectedBy();
    }
    public Teacher getTeacherInfos(Long Teacher_id) throws NoUserFound, IOException {
        Teacher stu = getTeacherById(Teacher_id);
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
    public Teacher getTeacherById(Long Teacher_id){
        return teacherRepository.getTeacherById(Teacher_id);
    }
    public HashMap<String,String>   UpdateTeacherData(MultipartFile file,Long id){
        HashMap<String,String> error_validation = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
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
            String file_old_image = "C:\\Users\\achba\\Desktop\\DashboardFinal\\DashBoardRest\\src\\main\\resources\\images\\"+Teacher.getFilename();
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
            Teacher.setFilename(fullName);
        } catch (IOException e) {
            error_validation.put("file_erreur","Failed to upload file");
        }
        if(!error_validation.isEmpty()){
            throw new validation_studentinfo(error_validation);
        }
        teacherRepository.save(Teacher);
        HashMap<String,String> success_message = new HashMap<String,String>();
        success_message.put("success","Teacher is created succefuly");
        return error_validation;
    }
    private boolean isValidGender(String gender) {
        return GenderPerson.Male.name().equalsIgnoreCase(gender) || GenderPerson.Female.name().equalsIgnoreCase(gender);
    }
    public HashMap<String,String> Update_Cin(String Cin,Long id) throws NoUserFound, ConstraintViolationException {
        HashMap<String,String> Cin_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher.getCin().equals(Cin)){
            return null;
        }
        if(Teacher == null){
            throw new NoUserFound("No User Found");
        }
        if(teacherRepository.existsTeacherByCin(Cin) && !Teacher.getCin().equals(Cin)){
            Cin_msg.put("Cin_unicity","Cin is Already Exist");
            throw new validation_studentinfo(Cin_msg);
        }
        Teacher.setCin(Cin);
        teacherRepository.save(Teacher);
        Cin_msg.put("Cin","Cin is updated successfuly");
        return Cin_msg;
    }
    public HashMap<String,String> Update_Nom(String Nom,Long id) throws NoUserFound {
        HashMap<String,String> Nom_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher == null){
            throw new NoUserFound("No User Found");
        }
        Teacher.setNom(Nom);
        teacherRepository.save(Teacher);
        Nom_msg.put("Nom","Nom is updated successfuly");
        return Nom_msg;
    }
    public HashMap<String,String> Update_Prenom(String Prenom,Long id) throws NoUserFound {
        HashMap<String,String> Prenom_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher == null){
            throw new NoUserFound("No User Found");
        }
        Teacher.setPrenom(Prenom);
        teacherRepository.save(Teacher);
        Prenom_msg.put("Prenom","Prenom is updated successfuly");
        return Prenom_msg;
    }
    public HashMap<String,String> Update_Address(String Address,Long id) throws NoUserFound {
        HashMap<String,String> Cin_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher == null){
            Cin_msg.put("no_user","No User Found");
            Cin_msg.put("address",Teacher.getAddress());
            throw new validation_studentinfo(Cin_msg);
        }
        Teacher.setAddress(Address);
        teacherRepository.save(Teacher);
        Cin_msg.put("Address","Address is updated successfuly");
        return Cin_msg;
    }
    public HashMap<String,String> update_Email(String Email,Long id) throws NoUserFound {
        HashMap<String,String> Email_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher == null){
            throw new NoUserFound("No User Found");
        }else if(Email.equals(Teacher.getEmail())){
            return null;
        }
        if(personRepository.existsPersonsByEmail(Email)){
            Email_msg.put("Email_exist","Email already exist");
            throw new validation_studentinfo(Email_msg);
        }
        Teacher.setEmail(Email);
        teacherRepository.save(Teacher);
        Email_msg.put("Email","Email is updated successfuly");
        return Email_msg;
    }
    public HashMap<String,String> Update_Phone(String Phone,Long id) throws NoUserFound {
        HashMap<String,String> Phone_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher == null){
            throw new NoUserFound("No User Found");
        }else if(Phone.equals(Teacher.getPhone())){
            return null;
        }
        if(personRepository.existsPersonsByPhone(Phone)){
            Phone_msg.put("Phone","Phone already exist");
            throw new validation_studentinfo(Phone_msg);
        }
        Teacher.setPhone(Phone);
        teacherRepository.save(Teacher);
        Phone_msg.put("Phone","Phone is updated successfuly");
        return Phone_msg;
    }
    public HashMap<String,String> Update_gender(String gender,Long id){
        HashMap<String,String> Gender_msg = new HashMap<>();
        if (!isValidGender(gender)) {
            Gender_msg.put("gender","gender must be Male or Female");
            throw new validation_studentinfo(Gender_msg);
        }
        Teacher Teacher = teacherRepository.getTeacherById(id);
        Teacher.setGender(gender);
        teacherRepository.save(Teacher);
        Gender_msg.put("gender","Gender is updated successfuly");
        return Gender_msg;
    }
    public HashMap<String,String> Update_Birthday(String birthday,Long id) throws NoUserFound {
        HashMap<String,String> Birthday_msg = new HashMap<>();
        Teacher Teacher = teacherRepository.getTeacherById(id);
        if(Teacher == null){
            throw new NoUserFound("No User Found");
        }else if(birthday.equals(Teacher.getBirthday())){
            return null;
        }

        Teacher.setBirthday(birthday);
        teacherRepository.save(Teacher);
        Birthday_msg.put("Birthday","Birthday is updated successfuly");
        return Birthday_msg;
    }
}
