package main.java.DTO;

public class CareerReportDTO {

    private String careerName;
    private int year;
    private int totalEnrolled;

    private int totalGraduated;

    @Override
    public String toString() {
        return "CareerReportDTO{" +
                "totalGraduated=" + totalGraduated +
                ", totalEnrolled=" + totalEnrolled +
                ", year=" + year +
                ", careerName='" + careerName + '\'' +
                '}';
    }
}
