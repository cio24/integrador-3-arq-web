package main.java.DTO;

public class CareerReportDTO {
    private int totalGraduated;
    private int totalEnrolled;
    private int year;
    private String careerName;

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
