package com.example.dashboardrest.Services.ServicePerson;

import com.example.dashboardrest.DAO.PersonDAO.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    public void deletePerson(List<Long> id){
        personRepository.deleteByIdIn(id);
    }
}
