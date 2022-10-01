package main.java.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "inscriptions")
public class Inscription {
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

    public Inscription(Student student, Career career, Timestamp inscriptionDate, Timestamp graduationDate) {
        this.student = student;
        this.career = career;
        this.inscriptionDate = inscriptionDate;
        this.graduationDate = graduationDate;
    }
}
