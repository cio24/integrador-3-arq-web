package main.java.controllers;

import main.java.DTO.CareerDTO;
import main.java.DTO.StudentDTO;
import main.java.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/careers")
public class CareerController {

    @Autowired
    CareerService careerSerivce;

    @PostMapping
    public void post(@RequestBody CareerDTO c){
        careerSerivce.save(c);
    }

    @GetMapping()
    public List<CareerDTO> getMany(@RequestParam String withStudents, @RequestParam String sortBy){
        if(withStudents.equals("true") && sortBy.equals("enrolledAmount"))
            return careerSerivce.findWithEnrolledStudents();

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }
}
