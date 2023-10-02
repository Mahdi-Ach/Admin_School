package com.example.dashboardrest.DAO.PersonDAO;

import com.example.dashboardrest.BO.Members.Student;
import com.example.dashboardrest.Projection.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository <Student,Long> {
    List<StudentProjection> findAllBy();
    Student getStudentById(Long id);
    boolean existsStudentByCne(String cne);
    @Modifying
    @Query("UPDATE Student s SET s.nom = CASE WHEN :nom<>'' AND :nom IS NOT NULL THEN :nom ELSE s.nom END," +
            "s.cne = CASE WHEN :cne<>'' AND :cne IS NOT NULL THEN :cne ELSE s.cne END," +
            "s.prenom = CASE WHEN :prenom<>'' AND :prenom IS NOT NULL THEN :prenom ELSE s.prenom END," +
            "s.email = CASE WHEN :email<>'' AND :email IS NOT NULL THEN :email ELSE s.email END," +
            "s.phone = CASE WHEN :phone<>'' AND :phone IS NOT NULL THEN :phone ELSE s.phone END," +
            "s.address = CASE WHEN :address<>'' AND :address IS NOT NULL THEN :address ELSE s.address END," +
            "s.birthday = CASE WHEN :birthday<>'' AND :birthday IS NOT NULL THEN :birthday ELSE s.birthday END," +
            "s.gender = CASE WHEN :gender<>'' AND :gender IS NOT NULL THEN :gender ELSE s.gender END," +
            "s.filename = CASE WHEN :filename<>'' AND :filename IS NOT NULL THEN :filename ELSE s.filename END WHERE s.id = :id")
    void update_fields(Long id,String cne,String nom, String prenom, String email, String phone, String address, String birthday,String gender,String filename);

}
