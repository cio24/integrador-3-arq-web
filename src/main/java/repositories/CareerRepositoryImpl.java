package main.java.repositories;

import main.java.entities.Career;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CareerRepositoryImpl implements CareerRepository {
    private EntityManager em;

    public CareerRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public Career save(Career c) {
        if (c.getId() == -1) {
            em.persist(c);
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

    @Override
    public Career findById(int id) {
        return this.em.createQuery("SELECT c FROM Career c WHERE c.id = :id", Career.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
