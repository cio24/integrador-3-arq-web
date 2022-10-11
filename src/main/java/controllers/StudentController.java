package main.java.controllers;

import main.java.DTO.StudentDTO;
import main.java.services.StudentSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/studends")
public class StudentController {

    @Autowired
    StudentSerivce studentSerivce;

    @RequestMapping(value = "/", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDTO post(){
        StudentDTO studentDTO = null;
        //create student dto a partir de los datos recibidos

        studentSerivce.save(studentDTO);
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDTO> getAllSortedByName(@RequestParam String sortBy){
        if(sortBy.equals("name"))
            return studentSerivce.getAllSortedByName();
        return null;
    }

}
