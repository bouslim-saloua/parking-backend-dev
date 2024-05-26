package com.emsi.parking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int numero;
    private boolean reserve;

    @ManyToOne
    private Parking parking;

    @OneToMany(mappedBy = "place")
    private List<Reservation> reservations;

    // Constructors
    public Place() {}

    public Place(Long id, int numero, Parking parking, boolean reserve) {
        this.id = id;
        this.numero = numero;
        this.parking = parking;
        this.reserve = reserve;
    }
}