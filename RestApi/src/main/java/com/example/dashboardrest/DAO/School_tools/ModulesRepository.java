package com.example.dashboardrest.DAO.School_tools;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.Projection.List_module_nom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModulesRepository extends JpaRepository<Modules,Long> {
    List<Modules> getModulesByIdIn(List<Long> id);
    Modules getModulesByModuleNom(String nomModule);
    Modules getModulesById(Long id);
    boolean existsModulesByModuleNom(String nom_Module);
    boolean existsModulesByCodeModule(String code_module);
    void deleteByIdIn(List<Long> id);
    List<Modules> findAll();
    List<List_module_nom> findAllProjectedBy();

}
