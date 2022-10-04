package main.java.repositories;

import main.java.entities.Career;
import main.java.entities.Student;

import java.util.List;

public interface StudentRepository {
    Student save(Student s);

    /**
     *
     * @param orderCriteria column name of the student entity to use to sort ascending students to be returned
     */
<<<<<<< HEAD
    List<Student> findAllSortedByName(String order);
=======
    List<Student> findAll(String orderCriteria);
>>>>>>> 4d0bc1dc34f851399a66012b8059eb1165e087d9

    Student findByBookNumber(int bookNumber);

    List<Student> findByGender(String gender);

    List<Student> findByCareerAndCity(Career c, String city);

    void deleteAll();

}
