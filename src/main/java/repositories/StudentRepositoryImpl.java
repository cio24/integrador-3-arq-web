package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import java.util.Collections;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student save(Student s) {
    	if (s.getBookNumber() != -1) {
    		em.getTransaction().begin();
    		em.persist(s);
    		em.getTransaction().commit();
    		
    	}else {
    		s = em.merge(s);
    	}
        return s;
    }

    @Override
    public List<Student> findAllSortedByName(String order) {
    	
    	List<Student> students;
    	Query query= em.createQuery("SELECT s FROM Students ORDER BY s.name :order");
		query.setParameter("order", order);
		students = query.getResultList();
		return students;
        
    }

    @Override
    public Student findByBookNumber(int bookNumber) {
        return null;
    }

    @Override
    public List<Student> findByGender(String gender) {
        return null;
    }

    @Override
    public List<Student> findByCareerAndCity(Career c, String city) {
        return null;
    }

    @Override
    public void deleteAll() {
        this.em.createQuery("delete from Student").executeUpdate();
    }
}
