package main.java.controllers;

import main.java.DTO.StudentDTO;
import main.java.services.StudentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentSerivce studentSerivce;

    @PostMapping()
    public StudentDTO save(@RequestBody StudentDTO s){
        return studentSerivce.save(s);
    }

    @GetMapping("/{id}")
    public StudentDTO getByBookName(@PathVariable String id){
        return studentSerivce.getByBookNumber(Integer.valueOf(id));
    }

    @GetMapping()
    public List<StudentDTO> getMany(@RequestParam(required = false) String gender, @RequestParam(required = false) String sortBy,
                                    @RequestParam(required = false) int careerId, @RequestParam(required = false) String city){
        if(gender != null)
            return  studentSerivce.getAllByGender(gender);

        if(sortBy != null && sortBy.equals("name"))
            return studentSerivce.getAllSortedByName();

        if(city != null && careerId != 0)
            return studentSerivce.findAllByCareerAndCity(careerId, city);

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @GetMapping("/findSortedByName")
    public List<StudentDTO> findAllSortedByName(){
        return studentSerivce.getAllSortedByName();
    }

}
