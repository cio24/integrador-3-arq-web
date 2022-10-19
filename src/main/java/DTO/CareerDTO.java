package main.java.DTO;

public class CareerDTO {
    private String name;

    private int id;

    public CareerDTO(){
        super();
    }

   public CareerDTO(String name) {
       this.name = name;
   }

    public String getName() {
        return name;
    }

    public int getId() { return id; }

    public void setId(int id){
        this.id = id;
    }
}
