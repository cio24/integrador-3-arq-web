package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Student s) {

    }

    @Override
    public List<Student> findAll(String orderCriteria) {
        return null;
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
}
