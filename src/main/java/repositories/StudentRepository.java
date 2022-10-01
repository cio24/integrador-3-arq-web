package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import java.util.List;

public interface StudentRepository {
    void save(Student s);

    /**
     *
     * @param orderCriteria column name of the student entity to use to sort ascending students to be returned
     */
    List<Student> findAll(String orderCriteria);

    Student findByBookNumber(int bookNumber);

    List<Student> findByGender(String gender);

    List<Student> findByCareerAndCity(Career c, String city);
}
