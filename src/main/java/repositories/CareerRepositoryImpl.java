package main.java.repositories;

import main.java.DTO.CareerReportDTO;
import main.java.entities.Career;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public class CareerRepositoryImpl implements CareerRepository {
    private EntityManager em;

    public CareerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Career c) {

    }

    @Override
    public List<Career> findWithEnrolledStudents() {
    	  
    	List <Career> careers =  em.createQuery("SELECT c.name FROM Career c, Inscription i WHERE c.id = i.career GROUP BY c.name HAVING COUNT(i.student) ORDER BY DESC").getResultList();
    	return careers;
    }
    
    

    @Override
    public void deleteAll() {
        this.em.createQuery("delete from Career").executeUpdate();
    }
}
