package com.example.dashboardrest.DAO.PersonDAO;

import com.example.dashboardrest.BO.Members.Persons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Persons,Long> {
    boolean existsPersonsByEmail(String Email);
    boolean existsPersonsByPhone(String Phone);
    void deleteByIdIn(List<Long> id);
}
