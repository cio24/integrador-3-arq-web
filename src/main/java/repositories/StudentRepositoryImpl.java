package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import javax.persistence.EntityManager;

import edu.isistan.dao.Persona;

import java.util.Collections;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student save(Student s) {
    	if (s.getBookNumber() != -1)
    		em.persist(s);
        else
    		s = em.merge(s);
        return s;
    }

    @Override
    public List<Student> findAll(String orderCriteria) {
    	
    	List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY s.?1")
    			.setParameter(1, orderCriteria)
    			.getResultList();
    	
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
