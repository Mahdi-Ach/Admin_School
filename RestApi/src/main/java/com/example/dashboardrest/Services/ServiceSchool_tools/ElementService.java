package com.example.dashboardrest.Services.ServiceSchool_tools;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.DAO.School_tools.ElementsRepository;
import com.example.dashboardrest.DAO.School_tools.ModulesRepository;
import com.example.dashboardrest.DTOs.School_tools.ElementsDto;
import com.example.dashboardrest.DTOs.School_tools.ElementsListDto;
import com.example.dashboardrest.GlobalException.NoUserFound;
import com.example.dashboardrest.GlobalException.validation_studentinfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ElementService {
    @Autowired
    private ElementsRepository elementsRepository;
    @Autowired
    private ModulesRepository modulesRepository;
    public HashMap<String,String> Create_Element(Elements elements,String nomModule){
        HashMap<String,String> msg_element = new HashMap<>();
        if(!modulesRepository.existsModulesByModuleNom(nomModule)){
            msg_element.put("module_exist","module doesnt exist");
            throw new validation_studentinfo(msg_element);
        }
        if(elementsRepository.existsElementsByElementNom(elements.getElementNom())){
            System.out.println("entererd");
            msg_element.put("element_unicity","This Name already exist");
            throw new validation_studentinfo(msg_element);
        }
        Modules modules = modulesRepository.getModulesByModuleNom(nomModule);
        elements.setModules(modules);
        elementsRepository.save(elements);
        msg_element.put("element_create","element is created succefully");
        return msg_element;
    }
    public HashMap<String,String> Delete_Element(List<Long> id) throws NoUserFound {
        HashMap<String,String> msg_element = new HashMap<>();
        List<Elements> elements = elementsRepository.getElementsByIdIn(id);
        if(elements.isEmpty()){
            throw new NoUserFound("No Element FOund");
        }
        elementsRepository.deleteByIdIn(id);
        msg_element.put("msg_delete","elemnt is deleted succefuly");
        return msg_element;
    }

    public List<ElementsListDto> FindAllElement(){
        List<ElementsListDto> elementsDtos = elementsRepository.findAll().stream().map(elements -> new ElementsListDto(elements.getId(),elements.getElementNom(),elements.getModules())).collect(Collectors.toList());
        return elementsDtos;
    }
    public ElementsDto FindElementwithId(Long Id) throws NoUserFound {
        Elements elements = elementsRepository.getElementsById(Id);
        if(elements == null){
            throw new NoUserFound("No element found");
        }
        ElementsDto elementsDtos = new ElementsDto(elements.getId(),elements.getElementNom(), elements.getModules().getModuleNom(),modulesRepository.findAllProjectedBy());

        return elementsDtos;
    }
    public HashMap<String,String> Update_ElementNom(Long id,String elementNom,String ModuleNom){
        HashMap<String,String> msg_element = new HashMap<>();
        if(elementNom != null){
            if(elementNom.isEmpty()){
                msg_element.put("required","Nom d'element is required");
                throw new validation_studentinfo(msg_element);
            }else{
                Elements elements = elementsRepository.getElementsById(id);
                if(elementNom.equals(elements.getElementNom())){
                    return null;
                }
                if(elementsRepository.existsElementsByElementNom(elementNom) && !elementNom.equals(elements.getElementNom())){
                    msg_element.put("element_unicity","element nom already exist");
                    throw new validation_studentinfo(msg_element);
                }
                elements.setElementNom(elementNom);
                elementsRepository.save(elements);
                msg_element.put("msg_element","Element is Updated succeffuly");
            }
        }else if(ModuleNom != null){
            if(ModuleNom.isEmpty()){
                msg_element.put("msg_element","Nom du Module n exist pas");
                throw new validation_studentinfo(msg_element);
            }else{
                System.out.println("module");
                Modules modules = modulesRepository.getModulesByModuleNom(ModuleNom);
                Elements elements = elementsRepository.getElementsById(id);
                if(modulesRepository.existsModulesByModuleNom(ModuleNom) && ModuleNom.equals(elements.getModules().getModuleNom())){
                    msg_element.put("msg_element","Modules nom already exist");
                    throw new validation_studentinfo(msg_element);
                }else if(!modulesRepository.existsModulesByModuleNom(ModuleNom)){
                    msg_element.put("msg_element","this module No mdoesnt exist");
                    throw new validation_studentinfo(msg_element);
                }
                elements.setModules(modules);
                elementsRepository.save(elements);
                msg_element.put("msg_element","Element is Updated succeffuly");
            }
        }else{
            msg_element.put("msg_element","obligatory a query paramtre");
        }

        return msg_element;
    }
    public HashMap<String,String> Update_Element_Mod(Long id,String ModuleNom){
        HashMap<String,String> msg_module = new HashMap<>();
        Modules modules = modulesRepository.getModulesByModuleNom(ModuleNom);
        if(modulesRepository.existsModulesByModuleNom(ModuleNom) && ModuleNom.equals(modules.getModuleNom())){
            msg_module.put("msg_element","Modules nom already exist");
            throw new validation_studentinfo(msg_module);
        }
        Elements elements = elementsRepository.getElementsById(id);
        elements.setModules(modules);
        msg_module.put("msg_element","Element is Updated succeffuly");
        return msg_module;
    }
}
