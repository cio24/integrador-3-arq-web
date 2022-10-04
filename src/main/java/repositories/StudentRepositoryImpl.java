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
<<<<<<< HEAD
    	if (s.getBookNumber() != -1) {
    		em.getTransaction().begin();
    		em.persist(s);
    		em.getTransaction().commit();
    		
    	}else {
    		s = em.merge(s);
    	}
=======
    	if (s.getBookNumber() != -1)
    		em.persist(s);
        else
    		s = em.merge(s);
>>>>>>> 4d0bc1dc34f851399a66012b8059eb1165e087d9
        return s;
    }

    @Override
<<<<<<< HEAD
    public List<Student> findAllSortedByName(String order) {
    	
    	List<Student> students;
    	Query query= em.createQuery("SELECT s FROM Students ORDER BY s.name :order");
		query.setParameter("order", order);
		students = query.getResultList();
		return students;
        
=======
    public List<Student> findAll(String orderCriteria) {
    	
    	List<Student> students = em.createQuery("SELECT s FROM Student s ORDER BY s.?1")
    			.setParameter(1, orderCriteria)
    			.getResultList();
    	
        return students;
>>>>>>> 4d0bc1dc34f851399a66012b8059eb1165e087d9
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
