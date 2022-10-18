package main.java.entities;

import javax.persistence.*;

@Entity
@Table(name = "careers")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id = -1;

    @Column
    private String name;

    public Career() {
        super();
    }

    public Career(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Career{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;

        if (!(o instanceof Career))
            return false;

        Career c = (Career) o;

        return this.toString().equals(c.toString());
    }
}
