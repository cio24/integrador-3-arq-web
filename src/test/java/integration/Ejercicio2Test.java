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

public class Ejercicio2Test {
    private static InscriptionRepository inscriptionRepository;
    private static StudentRepository studentRepository;
    private static CareerRepository careerRepository;
    private static Student cio, lu, agus;
    private static Career ingenieria, tudai, fisica;
    private static Inscription cioInscription, luInscription, agusInscription;

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
        agus = new Student(2342,"Agus","Bedrossian",birthdate,"female","balcarce");
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

    private static void createInscriptions(){
        createStudents();
        createCareers();
        cioInscription = new Inscription(cio,ingenieria);
        luInscription = new Inscription(lu,tudai);
        agusInscription = new Inscription(agus,tudai);
        inscriptionRepository.save(cioInscription);
        inscriptionRepository.save(luInscription);
        inscriptionRepository.save(agusInscription);
    }

    /**
     * 2) a) dar de alta un estudiante
     */
    @Test
    public void darDeAltaEstudianteTest(){
        Student s = studentRepository.save(cio);
        assertTrue(s.getBookNumber() != -1);
    }

    /**
     * 2) b) matricular un estudiante en una carrera
     */
    @Test
    public void matricularEstudianteTest(){
        studentRepository.save(cio);
        careerRepository.save(ingenieria);
        Inscription cioInscription = new Inscription(cio,ingenieria);
        inscriptionRepository.save(cioInscription);
        Inscription inscriptionFound = inscriptionRepository.findByStudentAndCareer(cio,ingenieria);
        assertEquals(cioInscription,inscriptionFound);
    }

    /**
     * 2) c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
     */
    @Test
    public void recuperarTodosLosEstudiantesTest(){
        createStudents();
        List<Student> studentsFound = studentRepository.findAllSortedByName();
        assertTrue(studentsFound.get(0).equals(agus) && studentsFound.get(1).equals(cio) && studentsFound.get(2).equals(lu));
    }

    /**
     * 2) d) recuperar un estudiante, en base a su número de libreta universitaria
     */
    @Test
    public void recuperarEstudianteSegunLibretaTest(){
        studentRepository.save(cio);
        Student studentFound = studentRepository.findByBookNumber(cio.getBookNumber());
        assertEquals(studentFound,cio);
    }

    /**
     * 2) e) recuperar todos los estudiantes, en base a su género.
     */
    @Test
    public void RecuperarEstudiantesPorGeneroTest(){
        createStudents();
        List<Student> studentsFound = studentRepository.findByGender("female");
        assertTrue(!studentsFound.contains(cio) && studentsFound.contains(lu) && studentsFound.contains(agus));
    }

    /**
     * 2) f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
     */
    @Test
    public void recuperarCarrerasConInscriptosTest(){
        createInscriptions();

        List<Career> careersFound = careerRepository.findWithEnrolledStudents();
        assertTrue(careersFound.get(0).equals(tudai) && careersFound.get(1).equals(ingenieria)  && !careersFound.contains(fisica));
    }

    /**
     * 2) g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
     */
    @Test
    public void recuperarEstudiantesPorCarreraYCiudadTest() {
        createInscriptions();
        List<Student> studentsFound = studentRepository.findByCareerAndCity(tudai,"tandil");
        // cio: de ingenieria y tandil, agus de tudai y balcarce y lu de tudai y tandil
        assertTrue(studentsFound.contains(lu) && !studentsFound.contains(agus) && !studentsFound.contains(cio));
    }

}
