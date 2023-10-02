package com.example.dashboardrest.DAO.School_tools;

import com.example.dashboardrest.BO.School_tools.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Notes,Long> {
}
