package main.java.repositories;

import main.java.DTO.CareerReportDTO;
import main.java.entities.Career;
import main.java.entities.Inscription;
import main.java.entities.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
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
        String query = "SELECT c.name, e.year, e.enrolled, IFNULL(g.graduated,0) AS graduated FROM careers c\n" +
                "\tJOIN (SELECT career_id, YEAR(inscriptionDate) AS year, COUNT(*) AS enrolled FROM inscriptions GROUP BY career_id, year)\n" +
                "\t\tAS e\n" +
                "        ON e.career_id = c.id \n" +
                "\tLEFT JOIN \n" +
                "\t\t(SELECT career_id, YEAR(graduationDate) AS year, COUNT(*) AS graduated FROM inscriptions where graduationDate IS NOT NULL GROUP BY career_id, year)\n" +
                "\t\tAS g \n" +
                "\t\tON g.career_id = e.career_id AND g.year = e.year\n" +
                " group by c.name, e.year, c.name, e.enrolled, graduated " +
                "ORDER BY c.name, e.year";

        List<Object[]> results =  this.em.createNativeQuery(query).getResultList();
        List<CareerReportDTO> reports = new ArrayList<>();
        for (Object[] r : results)
            reports.add(new CareerReportDTO((String)r[0],(Integer)r[1],(BigInteger)r[2],(BigInteger)r[3]));
        return reports;
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
