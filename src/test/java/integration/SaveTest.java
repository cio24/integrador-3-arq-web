package test.java.integration;

import main.java.entities.Career;
import main.java.repositories.CareerRepository;
import main.java.repositories.CareerRepositoryImpl;
import main.java.repositories.StudentRepositoryImpl;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertTrue;

public class SaveTest {
    @Test
    public void saveCareerTest(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        CareerRepository careerRepository = new CareerRepositoryImpl(em);
        Career ingenieria = careerRepository.save(new Career("Ingeniería de Sistemas"));
        int id = ingenieria.getId();
        assertTrue(id != -1);
    }
}
