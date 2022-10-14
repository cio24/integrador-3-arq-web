package main.java.controllers;

import main.java.DTO.StudentDTO;
import main.java.services.StudentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getAllSortedByName(@RequestParam String sortBy){
        if(sortBy.equals("name"))
            return studentSerivce.getAllSortedByName();
        return null;
    }

}
