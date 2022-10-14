package main.java.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class StudentDTO {
    private int documentNumber;
    private String name;
    private String surname;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp birthdate;
    private String gender;
    private String city;

    public StudentDTO(int documentNumber, String name, String surname, Timestamp birthdate, String gender, String city) {
        this.documentNumber = documentNumber;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.city = city;
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
}
