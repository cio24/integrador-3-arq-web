package main.java.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class StudentDTO {
    private int documentNumber;
    private String name;
    private String surname;
    private int bookNumber = -1;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp birthdate;
    private String gender;
    private String city;

    public StudentDTO(){
        super();
    }

    public StudentDTO(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city, int bookNumber) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
        this.bookNumber = bookNumber;
    }

    public StudentDTO(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city) {
        this(documentNumber,name,surname,birthdate,gender,city,-1);
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Timestamp getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public int getBookNumber() { return bookNumber; }

    public void setBookNumber(int bookNumber){
        this.bookNumber = bookNumber;
    }
}
