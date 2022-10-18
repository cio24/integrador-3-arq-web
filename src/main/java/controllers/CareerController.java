package main.java.controllers;

import main.java.DTO.CareerDTO;
import main.java.services.CareerService;
import main.java.services.StudentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/careers")
public class CareerController {

    @Autowired
    CareerService careerSerivce;

    @PostMapping
    public List<CareerDTO> findWithEnrolledStudents(){
        return careerSerivce.findWithEnrolledStudents();

    }
}
