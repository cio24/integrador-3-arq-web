package main.java.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "inscriptions")
public class Inscription implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn
    private Career career;

    @Column
    private Timestamp inscriptionDate;

    @Column
    private Timestamp graduationDate;

    public Inscription(){
        super();
    }

    public Inscription(Student student, Career career) {
        this(student,career,new Timestamp(new Date().getTime()));
    }

    public Inscription(Student student, Career career, Timestamp inscriptionDate) {
        this(student,career,inscriptionDate,null);
    }

    public Inscription(Student student, Career career, Timestamp inscriptionDate, Timestamp graduationDate) {
        this.student = student;
        this.career = career;
        this.inscriptionDate = inscriptionDate;
        this.graduationDate = graduationDate;
    }

    public Student getStudent() {
        return student;
    }

    public Career getCareer() {
        return career;
    }

    public Timestamp getInscriptionDate() {
        return inscriptionDate;
    }

    public Timestamp getGraduationDate() {
        return graduationDate;
    }

    public boolean equals(Object o){
        if (o == this)
            return true;

        if (!(o instanceof Inscription))
            return false;

        Inscription i = (Inscription) o;

        return this.student.equals(i.student) && this.career.equals(i.career);
    }
}
