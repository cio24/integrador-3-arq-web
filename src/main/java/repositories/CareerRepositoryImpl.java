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
    public Career save(Career c) {
        if (c.getId() == -1) {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();

        }else {
            c = em.merge(c);
        }
        return c;
    }

    @Override
    public List<Career> findWithEnrolledStudents() {
    	return em.createQuery("SELECT c FROM Inscription  i JOIN i.career c GROUP BY i.career ORDER BY COUNT(i.career) DESC").getResultList();
    }

    @Override
    public void deleteAll() {
        em.getTransaction().begin();
        this.em.createQuery("delete from Career").executeUpdate();
        em.getTransaction().commit();
    }
}
