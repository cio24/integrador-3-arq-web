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
    public void save(Inscription i) {
        this.em.persist(i);
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
        this.em.createQuery("delete from Inscription").executeUpdate();
    }

    @Override
    public Inscription findByStudentAndCareer(Student cio, Career ingenieria) {
        return null;
    }
}
