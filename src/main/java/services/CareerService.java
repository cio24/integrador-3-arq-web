package main.java.services;


import main.java.DTO.CareerDTO;
import main.java.entities.Career;
import main.java.repositories.CareerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CareerService {

    @Autowired
    CareerRepository careerRepository;

    public List<CareerDTO> findWithEnrolledStudents(){
        List<Career> careersFound = careerRepository.findWithEnrolledStudents();
        List<CareerDTO> careersDTO = new ArrayList<>();

        for(Career c: careersFound){
            careersDTO.add(new CareerDTO(c.getName()));
        }

        return careersDTO;
    }


}
