package com.example.dashboardrest.Services.ServiceSchool_tools;

import com.example.dashboardrest.BO.School_tools.Class_room;
import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.DAO.School_tools.FilierRepository;
import com.example.dashboardrest.DAO.School_tools.ModulesRepository;
import com.example.dashboardrest.DTOs.School_tools.ClassesListDto;
import com.example.dashboardrest.DTOs.School_tools.FilierDto;
import com.example.dashboardrest.DTOs.School_tools.FilierListDto;
import com.example.dashboardrest.DTOs.School_tools.ModulesDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.GlobalException.validation_studentinfo;
import com.example.dashboardrest.Projection.List_class_nom;
import com.example.dashboardrest.Projection.List_filier_nom;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Transactional
public class FilierService {
    @Autowired
    private FilierRepository filierRepository;
    public HashMap<String,String> Create_Filier(Filier filier){
        HashMap<String,String> msg_element = new HashMap<>();
        if(filierRepository.existsFilierByFilierNom(filier.getFilierNom())){
            msg_element.put("filier_unicity","This Name already exist");
            throw new validation_studentinfo(msg_element);
        }
        filierRepository.save(filier);
        msg_element.put("filier_create","Filier is created succefully");
        return msg_element;
    }
    public HashMap<String,String> Delete_Filier(List<Long> id) throws NoUserFound {
        HashMap<String,String> msg_module = new HashMap<>();
        List<Filier> filiers = filierRepository.getFilierByIdIn(id);
        if(filiers.isEmpty()){
            throw new NoUserFound("No Module FOund");
        }
        filierRepository.deleteByIdIn(id);
        msg_module.put("msg_delete","Filier is deleted succefuly");
        return msg_module;
    }

    public List<FilierListDto> FindAllFilier(){
        HashMap<String,Object> Element = new HashMap<>();
        List<FilierListDto> filierDtos = filierRepository.findAll().stream().map(filier -> new FilierListDto(filier.getId(), filier.getFilierNom())).collect(Collectors.toList());
        return filierDtos;
    }
    public List<List_filier_nom> findall_nomFilier(){
        return filierRepository.findAllProjectedBy();
    }
    public FilierListDto findFilierbyId(Long Id) throws NoUserFound {
        Filier filier = filierRepository.getFilierById(Id);
        if(filier == null){
            throw new NoUserFound("No element found");
        }
        FilierListDto filierListDto = new FilierListDto(filier.getId(),filier.getFilierNom());

        return filierListDto;
    }
    public HashMap<String,String> Update_Filier(Long id,String filierNom){
        HashMap<String,String> msg_filier = new HashMap<>();
        Filier filier = filierRepository.getFilierById(id);
        if(filierNom.equals(filier.getFilierNom())){
            return null;
        }
        if(filierRepository.existsFilierByFilierNom(filierNom)){
            msg_filier.put("msg_filier","Filier nom already exist");
            throw new validation_studentinfo(msg_filier);
        }

            filier.setFilierNom(filierNom);
        msg_filier.put("msg_filier","Filier is Updated succeffuly");
        return msg_filier;
    }
}
