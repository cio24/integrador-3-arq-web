package test.java.integration;

import main.java.entities.Career;
import main.java.entities.Inscription;
import main.java.entities.Student;
import main.java.repositories.CareerRepository;
import main.java.repositories.InscriptionRepository;
import main.java.repositories.StudentRepositoryImpl;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
import main.java.repositories.StudentRepository;
import org.junit.After;
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
    public static void instanciateObjects(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        studentRepository = new StudentRepositoryImpl(em);
        Timestamp birthdate = createTimestamp("31/03/1995");
        Student cio = new Student(1234,"Cio","Casado",birthdate,"male","mar del plata");
        Student lu = new Student(3231,"Lu","Blanco",birthdate,"female", "tandil");
        Student agus = new Student(2342,"Agus","BedRossian",birthdate,"female","tandil");
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
        ingenieria = new Career("Ingeniería de Sistemas");
        tudai = new Career("Tecnicatura Universitaria de Desarrollo de Aplicaciones Informaticas");
        fisica = new Career("Licenciatura en Física");

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
        studentRepository.save(cio);
        Student studentFound = studentRepository.findByBookNumber(cio.getBookNumber());
        assertEquals(studentFound,cio);
    }

    /**
     * b) matricular un estudiante en una carrera
     */
    @Test
    public void saveInscriptionTest(){
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
        List<Student> studentsFound = studentRepository.findAll("name");
		for (Student s: studentsFound)
			System.out.println(s);
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
