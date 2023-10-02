package com.example.dashboardrest.DAO.School_tools;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.BO.School_tools.Filier;
import com.example.dashboardrest.Projection.List_filier_nom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilierRepository extends JpaRepository<Filier,Long> {
    List<Filier> getFilierByIdIn(List<Long> id);
    Filier getFilierById(Long id);
    boolean existsFilierByFilierNom(String nom);
    void deleteByIdIn(List<Long> id);
    Filier getFilierByFilierNom(String nomfilier);
    List<List_filier_nom> findAllProjectedBy();
}
