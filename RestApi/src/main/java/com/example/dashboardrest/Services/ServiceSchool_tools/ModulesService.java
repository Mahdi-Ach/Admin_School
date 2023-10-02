package com.example.dashboardrest.Services.ServiceSchool_tools;

import com.example.dashboardrest.BO.School_tools.Class_room;
import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.DAO.School_tools.Class_RoomRepository;
import com.example.dashboardrest.DAO.School_tools.ElementsRepository;
import com.example.dashboardrest.DAO.School_tools.ModulesRepository;
import com.example.dashboardrest.DTOs.School_tools.ClassesListDto;
import com.example.dashboardrest.DTOs.School_tools.ElementsDto;
import com.example.dashboardrest.DTOs.School_tools.ModuleListDto;
import com.example.dashboardrest.DTOs.School_tools.ModulesDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.GlobalException.validation_studentinfo;
import com.example.dashboardrest.Projection.List_module_nom;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class ModulesService {
    @Autowired
    private ModulesRepository modulesRepository;
    @Autowired
    private Class_RoomRepository classRoomRepository;
    public HashMap<String,String> Create_Module(Modules modules,String nom_class){
        HashMap<String,String> msg_element = new HashMap<>();
        if(modulesRepository.existsModulesByModuleNom(modules.getModuleNom())){
            msg_element.put("module_unicity","This Name already exist");
            throw new validation_studentinfo(msg_element);
        }
        if(!classRoomRepository.existsClass_roomByClassNom(nom_class)){
            msg_element.put("module_unicity","This Class Doesnt exist");
            throw new validation_studentinfo(msg_element);
        }
        Class_room cls = classRoomRepository.getClass_roomByClassNom(nom_class);
        modules.setClasses(cls);
        modulesRepository.save(modules);
        msg_element.put("module_create","Module is created succefully");
        return msg_element;
    }
    public HashMap<String,String> Delete_Module(List<Long> id) throws NoUserFound {
        HashMap<String,String> msg_module = new HashMap<>();
        List<Modules> modules = modulesRepository.getModulesByIdIn(id);
        if(modules.isEmpty()){
            throw new NoUserFound("No Module FOund");
        }
        modulesRepository.deleteByIdIn(id);
        msg_module.put("msg_delete","Module is deleted succefuly");
        return msg_module;
    }
    public List<List_module_nom> findall_nomModule(){
        return modulesRepository.findAllProjectedBy();
    }
    public List<ModuleListDto> FindAllModule(){
        List<ModuleListDto> modulesDtos = modulesRepository.findAll().stream().map(modules -> new ModuleListDto(modules.getId(),modules.getModuleNom(),modules.getCodeModule(), modules.getClasses())).collect(Collectors.toList());
        return modulesDtos;
    }
    public ModulesDto FindmModulewithId(Long id) throws NoUserFound {
        Modules modules = modulesRepository.getModulesById(id);
        if(modules == null){
            throw new NoUserFound("No Module found");
        }
        ModulesDto modulesDto = new ModulesDto(modules.getId(),modules.getModuleNom(), modules.getCodeModule(),modules.getClasses().getClassNom(),classRoomRepository.findAllProjectedBy());

        return modulesDto;
    }
    public HashMap<String,String> Update_Module(Long id,String nomModule,String codeModule,String class_name) throws NoUserFound {
        HashMap<String,String> msg_module = new HashMap<>();
        if(nomModule != null){
            if(nomModule.isEmpty()){
                msg_module.put("required","Nom de la Module is required");
                throw new validation_studentinfo(msg_module);
            }else{
                Modules modules = modulesRepository.getModulesById(id);
                if(nomModule.equals(modules.getModuleNom())){
                    return null;
                }
                if(modulesRepository.existsModulesByModuleNom(nomModule) && !nomModule.equals(modules.getModuleNom())){
                    msg_module.put("module_unicity","element nom already exist");
                    throw new validation_studentinfo(msg_module);
                }
                modules.setModuleNom(nomModule);
                modulesRepository.save(modules);
                msg_module.put("msg_module","Class is Updated succeffuly");
            }
        }else if(codeModule != null){
            if(codeModule.isEmpty()){
                msg_module.put("required","Nom de la Module is required");
                throw new validation_studentinfo(msg_module);
            }else{
                Modules modules = modulesRepository.getModulesById(id);
                if(codeModule.equals(modules.getCodeModule())){
                    return null;
                }
                if(modulesRepository.existsModulesByCodeModule(codeModule) && !codeModule.equals(modules.getCodeModule())){
                    msg_module.put("module_unicity","element nom already exist");
                    throw new validation_studentinfo(msg_module);
                }
                modules.setCodeModule(codeModule);
                modulesRepository.save(modules);
                msg_module.put("msg_code","Module is Updated succeffuly");
            }
        } else if(class_name != null){
            System.out.println(class_name);
            if(class_name.isEmpty()){
                msg_module.put("msg_classRoom","Nom du Class n exist pas");
                throw new validation_studentinfo(msg_module);
            }else{
                System.out.println("module");
                Class_room classRoom = classRoomRepository.getClass_roomByClassNom(class_name);
                Modules modules = modulesRepository.getModulesById(id);
                if(classRoomRepository.existsClass_roomByClassNom(class_name) && classRoom.equals(modules.getClasses().getClassNom())){
                    msg_module.put("msg_class","Class nom already exist");
                    throw new validation_studentinfo(msg_module);
                }else if(!classRoomRepository.existsClass_roomByClassNom(class_name)){
                    msg_module.put("msg_class","this Class No mdoesnt exist");
                    throw new validation_studentinfo(msg_module);
                }
                modules.setClasses(classRoom);
                modulesRepository.save(modules);
                msg_module.put("msg_class","Module is Updated succeffuly");
            }
        }else{
            msg_module.put("msg_class","obligatory a query paramtre");
        }

        return msg_module;
    }

}
