package main.java.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int bookNumber;

    @Column
    private int documentNumber;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private Timestamp birthdate;

    @Column
    private String gender;

    @Column
    private String city;

    public Student(){
        super();
    }

    public Student(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "bookNumber=" + bookNumber +
                ", documentNumber=" + documentNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthdate=" + birthdate +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;

        if (!(o instanceof Student))
            return false;

        Student s = (Student) o;

        return this.toString().equals(s.toString());
    }
}
