package main.java.services;

import main.java.DTO.StudentDTO;
import main.java.entities.Student;
import main.java.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentSerivce {

    @Autowired
    StudentRepository studentRepository;

    public void save(StudentDTO studentDTO){
        //create entity
        Student student = null;
        studentRepository.save(student);
    }

    public List<StudentDTO> getAllSortedByName(){
        List<Student> students = studentRepository.findAllSortedByName();
        //generate dtos
        return null;
    }
}
