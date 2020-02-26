package com.verteiltesys.wgplanspringbootmysql;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name="aufgabe")
public class Aufgabe {
    private Integer id;
    private String name;
    private String aufTitel;
    private String aufBeschr;
    private Date deadline;
    private boolean done;


    public Aufgabe(){
    }

    public Aufgabe(Integer id, String name, String aufTitel, String aufBeschr, Date deadline, boolean done) {
        this.id = id;
        this.name = name;
        this.aufTitel = aufTitel;
        this.aufBeschr = aufBeschr;
        this.deadline = deadline;
        this.done = done;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() { //Generated getter setter
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAufTitel() {
        return aufTitel;
    }

    public void setAufTitel(String aufTitel) {
        this.aufTitel = aufTitel;
    }

    public String getAufBeschr() {
        return aufBeschr;
    }

    public void setAufBeschr(String aufBeschr) {
        this.aufBeschr = aufBeschr;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
