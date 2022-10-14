package main.java.services;

import main.java.DTO.StudentDTO;
import main.java.entities.Student;
import main.java.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentSerivce {

    @Autowired
    StudentRepository studentRepository;

    public void save(StudentDTO sDTO){
        //create entity
        Student s = new Student(sDTO.getDocumentNumber(),
                sDTO.getName(), sDTO.getSurname(), sDTO.getBirthdate(),
                sDTO.getGender(), sDTO.getCity());
        studentRepository.save(s);
    }

    public List<StudentDTO> getAllSortedByName(){
        List<Student> students = studentRepository.findAllSortedByName();
        //generate dtos
        return null;
    }
}
