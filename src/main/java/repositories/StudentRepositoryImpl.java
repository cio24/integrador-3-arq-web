package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Queue;

public class StudentRepositoryImpl implements StudentRepository {
    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Student save(Student s) {
    	if (s.getBookNumber() == -1) {
    		em.getTransaction().begin();
    		em.persist(s);
    		em.getTransaction().commit();
    		
    	}else {
    		s = em.merge(s);
    	}
        return s;
    }

    @Override
    public List<Student> findAllSortedByName() {
        List<Student> students;
        Query query = em.createQuery("SELECT s FROM Student s ORDER BY s.name");
        students = query.getResultList();
        return students;
    }

    @Override
    public Student findByBookNumber(int bookNumber) {
        return (Student) this.em.createQuery("SELECT s FROM Student s WHERE s.bookNumber = :bookNumber")
                .setParameter("bookNumber", bookNumber)
                .getSingleResult();
    }

    @Override
    public List<Student> findByGender(String gender) {
        return this.em.createQuery("SELECT s FROM Student s WHERE s.gender = :gender")
                .setParameter("gender", gender)
                .getResultList();
    }

    @Override
    public List<Student> findByCareerAndCity(Career c, String city) {
        return this.em.createQuery("select s from Inscription i join i.student s join i.career c where c.id = :careerId and s.city = :city")
                .setParameter("careerId",c.getId())
                .setParameter("city",city)
                .getResultList();
    }

    @Override
    public void deleteAll() {
        em.getTransaction().begin();
        this.em.createQuery("delete from Student").executeUpdate();
        em.getTransaction().commit();

    }
}
