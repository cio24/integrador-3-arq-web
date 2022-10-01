package main.java.repositories;

import main.java.entities.Career;
import java.util.List;

public interface CareerRepository {
    void save(Career c);

    /**
     * find all careers that have enrolled students and sort by amount of enrolled
     */
    List<Career> findWithEnrolledStudents();
}
