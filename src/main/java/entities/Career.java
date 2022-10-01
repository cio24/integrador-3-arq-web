package main.java.entities;

import javax.persistence.*;

@Entity
@Table(name = "careers")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String name;

    public Career() {
        super();
    }

    public Career(String name) {
        this.name = name;
    }
}
