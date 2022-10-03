package test.java.integration;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.java.entities.Student;
import main.java.repositories.CareerRepositoryImpl;
import main.java.repositories.InscriptionRepositoryImpl;
import main.java.repositories.StudentRepositoryImpl;

public class Main {

	public static void main(String[] args) {
		
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
        EntityManager em = emf.createEntityManager();
        
        
        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl(em);
        CareerRepositoryImpl careerRepository = new CareerRepositoryImpl (em);
        InscriptionRepositoryImpl inscriptionRepository = new InscriptionRepositoryImpl(em);
        
    
        Timestamp birthdate = createTimestamp("31/03/1995");
        Student cio = new Student(1234,"Cio","Casado",birthdate,"male","mar del plata");
        Student lu = new Student(3231,"Lu","Blanco",birthdate,"female", "tandil");
        Student agus = new Student(2342,"Agus","BedRossian",birthdate,"female","tandil");
        
        
        /**
         * a) dar de alta un estudiante
         */
        
        studentRepository.save(cio);
        studentRepository.save(lu);
        studentRepository.save(agus);

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

}
