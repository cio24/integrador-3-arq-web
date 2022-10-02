package main.java.repositories;

import main.java.entities.Career;

import javax.persistence.EntityManager;
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
        return null;
    }

    @Override
    public void deleteAll() {
        this.em.createQuery("delete from Career").executeUpdate();
    }
}
