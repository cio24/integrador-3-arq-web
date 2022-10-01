package test.java.integration;

import main.java.entities.Student;
import main.java.repositories.StudentRepositoryImpl;
import main.java.repositories.StudentRepository;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentRepositoryTest {
    private static StudentRepository studentRepository;
    private static Student cio;
    private static Student lu;
    private static Student agus;

    @BeforeClass
    static void instanciateObjects(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl(em);
        Timestamp birthdate = createTimestamp("31/03/1995");
        Student cio = new Student(1234,"Cio","Casado",birthdate,"male");
        Student lu = new Student(3231,"Lu","Blanco",birthdate,"female");
        Student agus = new Student(2342,"Agus","BedRossian",birthdate,"female");
    }

    private static Timestamp createTimestamp(String dateString){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long time = date.getTime();
        return new Timestamp(time);
    }

    @Test
    public void saveAndFindByBookNumberTest(){
        studentRepository.save(cio);
        Student studentFound = studentRepository.findByBookNumber(cio.getBookNumber());
        assertEquals(studentFound,cio);
    }

    @Test
    public void findAllTest(){
        studentRepository.save(cio);
        studentRepository.save(lu);
        studentRepository.save(agus);
        List<Student> studentsFound = studentRepository.findAll("name");
        assertTrue(studentsFound.get(0).equals(agus) && studentsFound.get(1).equals(cio) && studentsFound.get(2).equals(lu));
    }

    public void findByGenderTest(){
        studentRepository.save(cio);
        studentRepository.save(lu);
        studentRepository.save(agus);
        List<Student> studentsFound = studentRepository.findByGender("female");
    }

}
