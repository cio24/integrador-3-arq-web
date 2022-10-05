package main.java.DTO;

import main.java.entities.Inscription;

import java.math.BigInteger;

public class CareerReportDTO {

    private String careerName;
    private Integer year;
    private BigInteger totalEnrolled;

    private BigInteger totalGraduated;

    public CareerReportDTO(String careerName, Integer year, BigInteger totalEnrolled, BigInteger totalGraduated) {
        this.careerName = careerName;
        this.year = year;
        this.totalEnrolled = totalEnrolled;
        this.totalGraduated = totalGraduated;
    }

    @Override
    public String toString() {
        return "CareerReportDTO{" +
                "totalGraduated=" + totalGraduated +
                ", totalEnrolled=" + totalEnrolled +
                ", year=" + year +
                ", careerName='" + careerName + '\'' +
                '}';
    }

    public boolean equals(Object o){
        if (o == this)
            return true;

        if (!(o instanceof CareerReportDTO))
            return false;

        CareerReportDTO c = (CareerReportDTO) o;

        return this.toString().equals(c.toString());
    }
}
