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

    }

    @Override
    public List<CareerReportDTO> getReports() {
        return null;
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
