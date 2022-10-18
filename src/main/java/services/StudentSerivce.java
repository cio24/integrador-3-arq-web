package main.java.services;

import main.java.DTO.StudentDTO;
import main.java.entities.Student;
import main.java.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public StudentDTO getByBookNumber(int bookNumber){
        Student s = studentRepository.findByBookNumber(bookNumber);
        return new StudentDTO(s.getDocumentNumber(),s.getName(),s.getSurname(),s.getBirthdate(),s.getGender(),s.getCity());
    }

    public List<StudentDTO> getAllSortedByName(){
        List<Student> students = studentRepository.findAllSortedByName();
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students){
            studentsDTO.add(new StudentDTO(s.getDocumentNumber(),s.getName(),s.getSurname(),s.getBirthdate(),s.getGender(),s.getCity()));
        }
        return studentsDTO;
    }

    public List<StudentDTO> getAllByGender(String gender){
        List<Student> students = this.studentRepository.findByGender(gender);
        List<StudentDTO> studentsDTO = new ArrayList<>();

        for(Student s: students){
            studentsDTO.add(new StudentDTO(s.getDocumentNumber(),s.getName(),s.getSurname(),s.getBirthdate(),s.getGender(),s.getCity()));
        }
        return studentsDTO;
    }

    public List<StudentDTO> findAllByCareerAndCity(String careerId, String city){
        List<Student> students = this.studentRepository.findAllByCareerAndCity(careerId, city);
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for(Student student: students){
            studentDTOS.add(new StudentDTO(student.getDocumentNumber(), student.getName(), student.getSurname(),
                    student.getBirthdate(), student.getGender(), student.getCity()));
        }
        return studentDTOS;
    }
}
