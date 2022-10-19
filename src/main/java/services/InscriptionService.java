package main.java.services;

import main.java.DTO.CareerReportDTO;
import main.java.DTO.InscriptionDTO;
import main.java.entities.Career;
import main.java.entities.Inscription;
import main.java.entities.Student;
import main.java.repositories.CareerRepository;
import main.java.repositories.InscriptionRepository;
import main.java.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CareerRepository careerRepository;

    public InscriptionDTO save(InscriptionDTO iDTO){
        Student s = studentRepository.findByBookNumber(iDTO.getStudentBookNumber());
        Career c = careerRepository.findById(iDTO.getCareerId());
        inscriptionRepository.save(new Inscription(s,c));
        return iDTO;
    }

    public List<CareerReportDTO> getReports(){
        List<CareerReportDTO> report = inscriptionRepository.getReports();
        return report;
    }
}
