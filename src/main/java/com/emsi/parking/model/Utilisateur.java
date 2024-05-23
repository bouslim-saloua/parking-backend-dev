package com.emsi.parking.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Utilisateur implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
private String nom;
private String prenom;
private String email;
private String cin;
private String password;
private String codeQr;
private String codeQrImagePath;
private String telephone;
private String role;

@OneToMany(mappedBy = "utilisateur")
private List<Reservation> reservations;
}
