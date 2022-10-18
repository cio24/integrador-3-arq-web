package main.java.repositories;

import main.java.entities.Career;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerRepository {
    Career save(Career c);

    /**
     * find all careers that have enrolled students and sort by amount of enrolled
     */
    List<Career> findWithEnrolledStudents();

    void deleteAll();
}
