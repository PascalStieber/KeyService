package de.keyservice.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "Adresse")
public class Adresse implements Serializable {

    private static final long serialVersionUID = 1L;

    public Adresse() {
    }

    @Id
    private long id;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

}