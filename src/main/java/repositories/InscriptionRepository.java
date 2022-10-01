package main.java.repositories;

import main.java.DTO.CareerReportDTO;
import main.java.entities.Career;
import main.java.entities.Inscription;

import java.util.List;

public interface InscriptionRepository {
    /**
     * Enroll a student in a career
     * @param i inscription that contains the student and the career
     */
    void save(Inscription i);

    /**
     * get reports with data of the careers
     */
    List<CareerReportDTO> getReports();
}
