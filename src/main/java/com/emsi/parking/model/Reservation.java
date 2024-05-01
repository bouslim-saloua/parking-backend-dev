package com.emsi.parking.model;

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
public class Reservation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date dateReservation;
	private String status;
	private Date dateEntree;
	private Date dateSortie;
	@ManyToOne
	private Utilisateur utilisateur;
	@ManyToOne
	private Place place;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paiement_id", referencedColumnName = "id")
	private Paiement paiement;
	
	

}
