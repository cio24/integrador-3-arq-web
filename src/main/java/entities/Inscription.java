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
}
