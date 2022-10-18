package main.java.controllers;

import main.java.DTO.StudentDTO;
import main.java.services.StudentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentSerivce studentSerivce;

    @PostMapping
    public void post(@RequestBody StudentDTO s){
        studentSerivce.save(s);
    }

    @GetMapping("/{id}")
    public StudentDTO getByBookName(@PathVariable String id){
        return studentSerivce.getByBookNumber(Integer.valueOf(id));
    }

    @GetMapping()
    public List<StudentDTO> getAllByGender(@RequestParam String gender){
        return studentSerivce.getAllByGender(gender);
    }

    @GetMapping()
    public List<StudentDTO> findAllSortedByName(){
        return studentSerivce.getAllSortedByName();
    }
}
