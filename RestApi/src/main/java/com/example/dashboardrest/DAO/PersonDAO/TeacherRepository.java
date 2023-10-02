package com.example.dashboardrest.DAO.PersonDAO;

import com.example.dashboardrest.BO.Members.Teacher;
import com.example.dashboardrest.Projection.TeacherProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    List<TeacherProjection> findAllProjectedBy();
    Teacher getTeacherById(Long id);

    boolean existsTeacherByCin(String cin);
}
