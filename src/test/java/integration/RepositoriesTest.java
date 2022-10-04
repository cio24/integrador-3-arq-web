package test.java.integration;

import main.java.entities.Career;
import main.java.entities.Inscription;
import main.java.entities.Student;
import main.java.repositories.*;
import org.junit.After;
import org.junit.Before;
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

import static org.junit.Assert.*;

public class RepositoriesTest {
    private static InscriptionRepository inscriptionRepository;
    private static StudentRepository studentRepository;
    private static CareerRepository careerRepository;
    private static Student cio, lu, agus;
    private static Career ingenieria, tudai, fisica;

    @BeforeClass
    public static void instantiateEntityManagerAndRepositories(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl(em);
        inscriptionRepository = new InscriptionRepositoryImpl(em);
        careerRepository = new CareerRepositoryImpl(em);
    }

    @Before
    public void instantiateEntities(){
        Timestamp birthdate = createTimestamp("31/03/1995");
        cio = new Student(1234,"Cio","Casado",birthdate,"male","mar del plata");
        lu = new Student(3231,"Lu","Blanco",birthdate,"female", "tandil");
        agus = new Student(2342,"Agus","Bedrossian",birthdate,"female","tandil");
        ingenieria = new Career("Ingeniería de Sistemas");
        tudai = new Career("Tecnicatura Universitaria de Desarrollo de Aplicaciones Informaticas");
        fisica = new Career("Licenciatura en Física");
    }

    @After
    public void deleteEntities(){
        inscriptionRepository.deleteAll();
        studentRepository.deleteAll();
        careerRepository.deleteAll();
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

    private static void createStudents(){
        studentRepository.save(cio);
        studentRepository.save(lu);
        studentRepository.save(agus);
    }

    private static void createCareers(){
        careerRepository.save(ingenieria);
        careerRepository.save(tudai);
        careerRepository.save(fisica);
    }

    /**
     * a) dar de alta un estudiante
     * d) recuperar un estudiante, en base a su número de libreta universitaria
     */
    @Test
    public void saveAndFindStudentsByBookNumberTest(){
        Timestamp birthdate = createTimestamp("31/03/1995");
        Student cio = new Student(1234,"Cio","Casado",birthdate,"male","mar del plata");
        studentRepository.save(cio);
        Student studentFound = studentRepository.findByBookNumber(cio.getBookNumber());
        assertEquals(studentFound,cio);
    }

    /**
     * b) matricular un estudiante en una carrera
     */
    @Test
    public void saveInscriptionTest(){
        Timestamp birthdate = createTimestamp("31/03/1995");
        Student cio = new Student(1234,"Cio","Casado",birthdate,"male","mar del plata");
        studentRepository.save(cio);

        Career ingenieria = new Career("Ingeniería de Sistemas");
        careerRepository.save(ingenieria);

        Inscription cioInscription = new Inscription(cio,ingenieria);
        inscriptionRepository.save(cioInscription);
        Inscription inscriptionFound = inscriptionRepository.findByStudentAndCareer(cio,ingenieria);
        assertEquals(cioInscription,inscriptionFound);
    }

    /**
     * c)recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
     */
    @Test
    public void findAllStudentsTest(){
        createStudents();
        List<Student> studentsFound = studentRepository.findAllSortedByName();
        assertTrue(studentsFound.get(0).equals(agus) && studentsFound.get(1).equals(cio) && studentsFound.get(2).equals(lu));
    }

    /**
     * e) recuperar todos los estudiantes, en base a su género.
     */
    @Test
    public void findStudentsByGenderTest(){
        createStudents();
        List<Student> studentsFound = studentRepository.findByGender("female");

        assertTrue(!studentsFound.contains(cio) && studentsFound.contains(lu) && studentsFound.contains(agus));
    }

    /**
     * f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
     */
    @Test
    public void findCareersWithEnrolledStudentsTest(){
        createStudents();
        createCareers();

        Timestamp inscriptionDate = createTimestamp("01/01/2022");
        Inscription cioInscription = new Inscription(cio,ingenieria);
        Inscription luInscription = new Inscription(lu,tudai);
        Inscription agusInscription = new Inscription(agus,tudai);

        inscriptionRepository.save(cioInscription);
        inscriptionRepository.save(luInscription);
        inscriptionRepository.save(agusInscription);

        List<Career> careersFound = careerRepository.findWithEnrolledStudents();
        assertTrue(!careersFound.contains(fisica) && careersFound.contains(ingenieria) && careersFound.contains(tudai));
    }

    /**
     * g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
     */
    @Test
    public void findStudentsByCareerAndCity() {
        createStudents();
        createCareers();

        List<Student> studentsFound = studentRepository.findByCareerAndCity(ingenieria,"Tandil");
        assertTrue(!studentsFound.contains(cio) && studentsFound.contains(lu) && studentsFound.contains(agus));
    }

}
