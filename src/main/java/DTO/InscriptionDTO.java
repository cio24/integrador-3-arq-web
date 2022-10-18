package main.java.DTO;

public class InscriptionDTO {

    private int studentBookNumber;

    private int careerId;

    public InscriptionDTO(){
        super();
    }

    public InscriptionDTO(int studentBookNumber, int careerId) {
        this.studentBookNumber = studentBookNumber;
        this.careerId = careerId;
    }

    public int getStudentBookNumber() {
        return studentBookNumber;
    }

    public int getCareerId() {
        return careerId;
    }
}
