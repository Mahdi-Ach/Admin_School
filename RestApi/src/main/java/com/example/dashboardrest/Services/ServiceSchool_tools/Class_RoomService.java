package com.example.dashboardrest.Services.ServiceSchool_tools;

import com.example.dashboardrest.BO.School_tools.Class_room;
import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.DAO.School_tools.Class_RoomRepository;
import com.example.dashboardrest.DAO.School_tools.ElementsRepository;
import com.example.dashboardrest.DAO.School_tools.FilierRepository;
import com.example.dashboardrest.DTOs.School_tools.ClassListDto;
import com.example.dashboardrest.DTOs.School_tools.ClassesListDto;
import com.example.dashboardrest.DTOs.School_tools.ElementsDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.GlobalException.validation_studentinfo;
import com.example.dashboardrest.Projection.List_class_nom;
import com.example.dashboardrest.Projection.List_module_nom;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class Class_RoomService {
    @Autowired
    private Class_RoomRepository classRoomRepository;
    @Autowired
    private FilierRepository filierRepository;
    public HashMap<String,String> Create_Class(Class_room classRoom,String nomFilier){
        HashMap<String,String> msg_class = new HashMap<>();
        if(classRoomRepository.existsClass_roomByClassNom(classRoom.getClassNom())){
            msg_class.put("class_unicity","This Name already exist");
            throw new validation_studentinfo(msg_class);
        }
        if(!filierRepository.existsFilierByFilierNom(nomFilier)){
            msg_class.put("filier_unicity","This Class Doesnt exist");
            throw new validation_studentinfo(msg_class);
        }
        Filier flr = filierRepository.getFilierByFilierNom(nomFilier);
        classRoom.setFilier(flr);
        classRoomRepository.save(classRoom);
        msg_class.put("module_create","Class is created succefully");
        return msg_class;
    }
    public HashMap<String,String> Delete_Class(List<Long> id) throws NoUserFound {
        HashMap<String,String> msg_class = new HashMap<>();
        System.out.println(id);
        List<Class_room> classRooms = classRoomRepository.getClass_roomByIdIn(id);
        System.out.println(classRooms);
        if(classRooms.isEmpty()){
            throw new NoUserFound("No Module FOund");
        }
        classRoomRepository.deleteByIdIn(id);
        msg_class.put("msg_delete","Class is deleted succefuly");
        return msg_class;
    }

    public List<ClassListDto> FindAllClass(){
        HashMap<String,Object> Element = new HashMap<>();
        List<ClassListDto> classListDtos = classRoomRepository.findAll().stream().map(classes -> new ClassListDto(classes.getId(),classes.getClassNom(),classes.getFilier())).collect(Collectors.toList());
        return classListDtos;
    }
    public ClassesListDto FindElementwithId(Long Id) throws NoUserFound {
        Class_room classRoom = classRoomRepository.getClass_roomById(Id);
        if(classRoom == null){
            throw new NoUserFound("No element found");
        }
        ClassesListDto classesListDto = new ClassesListDto(classRoom.getId(),classRoom.getClassNom(), classRoom.getFilier().getFilierNom(),filierRepository.findAllProjectedBy());

        return classesListDto;
    }
    public List<List_class_nom> findall_nomClass(){
        return classRoomRepository.findAllProjectedBy();
    }
    public HashMap<String,String> Update_Class(Long id,String classNom,String nomfilier){
        HashMap<String,String> msg_element = new HashMap<>();
        if(classNom != null){
            if(classNom.isEmpty()){
                msg_element.put("required","Nom de la class is required");
                throw new validation_studentinfo(msg_element);
            }else{
                Class_room classRoom = classRoomRepository.getClass_roomById(id);
                if(classRoom.equals(classRoom.getClassNom())){
                    return null;
                }
                if(classRoomRepository.existsClass_roomByClassNom(classNom) && !classNom.equals(classRoom.getClassNom())){
                    msg_element.put("class_unicity","element nom already exist");
                    throw new validation_studentinfo(msg_element);
                }
                classRoom.setClassNom(classNom);
                classRoomRepository.save(classRoom);
                msg_element.put("msg_class","Class is Updated succeffuly");
            }
        }else if(nomfilier != null){
            System.out.println(nomfilier);
            if(nomfilier.isEmpty()){
                msg_element.put("msg_filier","Nom du Filier n exist pas");
                throw new validation_studentinfo(msg_element);
            }else{
                System.out.println("module");
                Filier filier = filierRepository.getFilierByFilierNom(nomfilier);
                Class_room classRoom = classRoomRepository.getClass_roomById(id);
                if(filierRepository.existsFilierByFilierNom(nomfilier) && nomfilier.equals(classRoom.getFilier().getFilierNom())){
                    msg_element.put("msg_filier","Modules nom already exist");
                    throw new validation_studentinfo(msg_element);
                }else if(!filierRepository.existsFilierByFilierNom(nomfilier)){
                    msg_element.put("msg_element","this module No mdoesnt exist");
                    throw new validation_studentinfo(msg_element);
                }
                classRoom.setFilier(filier);
                classRoomRepository.save(classRoom);
                msg_element.put("msg_class","Class is Updated succeffuly");
            }
        }else{
            msg_element.put("msg_class","obligatory a query paramtre");
        }

        return msg_element;
    }
}
