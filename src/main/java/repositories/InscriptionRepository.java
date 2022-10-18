package main.java.repositories;

import main.java.DTO.CareerReportDTO;
import main.java.entities.Career;
import main.java.entities.Inscription;
import main.java.entities.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepository {
    /**
     * Enroll a student in a career
     * @param i inscription that contains the student and the career
     */
    Inscription save(Inscription i);

    /**
     * get reports with data of the careers
     */
    List<CareerReportDTO> getReports();

    void deleteAll();

    Inscription findByStudentAndCareer(Student cio, Career ingenieria);
}
