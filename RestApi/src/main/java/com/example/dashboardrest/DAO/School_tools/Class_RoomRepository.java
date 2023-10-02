package com.example.dashboardrest.DAO.School_tools;

import com.example.dashboardrest.BO.School_tools.Class_room;
import com.example.dashboardrest.BO.School_tools.Modules;
import com.example.dashboardrest.Projection.List_class_nom;
import com.example.dashboardrest.Projection.List_module_nom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Class_RoomRepository extends JpaRepository<Class_room,Long> {
    List<Class_room> getClass_roomByIdIn(List<Long> id);
    Class_room getClass_roomById(Long id);
    boolean existsClass_roomByClassNom(String nom_class);
    void deleteByIdIn(List<Long> id);
    List<List_class_nom> findAllProjectedBy();
    Class_room getClass_roomByClassNom(String classNom);
}
