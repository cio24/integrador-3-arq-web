package main.java.repositories;

import main.java.DTO.CareerReportDTO;
import main.java.entities.Career;
import main.java.entities.Inscription;
import main.java.entities.Student;

import javax.persistence.EntityManager;
import java.util.List;

public class InscriptionRepositoryImpl implements InscriptionRepository {
    private EntityManager em;

    public InscriptionRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public Inscription save(Inscription i) {
        if (i.getId() == -1) {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();

        }else {
            i = em.merge(i);
        }
        return i;
    }

    @Override
    public List<CareerReportDTO> getReports() {
        String query = "SELECT c.name, e.year, e.enrolled, IFNULL(g.graduated,'0') AS graduated FROM careers c\n" +
                "\tJOIN (SELECT career, YEAR(inscriptionDate) AS year, COUNT(*) AS enrolled FROM inscriptions GROUP BY career, year)\n" +
                "\t\tAS e\n" +
                "        ON e.career = c.careerId \n" +
                "\tLEFT JOIN \n" +
                "\t\t(SELECT career, YEAR(graduationDate) AS year, COUNT(*) AS graduated FROM inscriptions where graduationDate IS NOT NULL GROUP BY career, year)\n" +
                "\t\tAS g \n" +
                "\t\tON g.career = e.career AND g.year = e.year\n" +
                "ORDER BY c.careerId, e.year";

        return this.em.createNativeQuery(query,CareerReportDTO.class).getResultList();
    }

    @Override
    public void deleteAll() {
        em.getTransaction().begin();
        this.em.createQuery("delete from Inscription").executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public Inscription findByStudentAndCareer(Student s, Career c) {
        return this.em.createQuery("select i from Inscription i where i.student = :s and i.career = :c",Inscription.class)
                .setParameter("s",s)
                .setParameter("c",c)
                .getSingleResult();
    }
}
