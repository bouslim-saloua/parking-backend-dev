package com.emsi.parking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateReservation;
	private String status;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateEntree;
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateSortie;
	private int heureEntree;
	private int heureSortie;
        
       // @JsonBackReference
	@ManyToOne
	private Utilisateur utilisateur;
        
       // @JsonBackReference
	@ManyToOne
	private Place place;
        
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paiement_id", referencedColumnName = "id")
	private Paiement paiement;
	public Reservation(){

	}

}
