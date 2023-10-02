package com.example.dashboardrest.DAO.School_tools;

import com.example.dashboardrest.BO.School_tools.Elements;
import com.example.dashboardrest.Projection.List_Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementsRepository extends JpaRepository <Elements,Long> {
    List<Elements> getElementsByIdIn(List<Long> id);
    Elements getElementsById(Long id);
    boolean existsElementsByElementNom(String nom);
    void deleteByIdIn(List<Long> id);
}
