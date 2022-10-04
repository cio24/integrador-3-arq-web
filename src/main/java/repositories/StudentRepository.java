package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import java.util.List;

public interface StudentRepository {
    Student save(Student s);

    List<Student> findAllSortedByName();

    Student findByBookNumber(int bookNumber);

    List<Student> findByGender(String gender);

    List<Student> findByCareerAndCity(Career c, String city);

    void deleteAll();

}
