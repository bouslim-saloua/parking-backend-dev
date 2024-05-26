package com.emsi.parking.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

 @JsonManagedReference
@OneToMany(mappedBy = "utilisateur")
private List<Reservation> reservations;

@ManyToMany (fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "utilisateur_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles = new HashSet<>();

 //Utilisateur utilisateur = new Utilisateur( signUpRequest.getNom(), signUpRequest.getPrenom(),signUpRequest.getTelephone(), signUpRequest.getEmail(),signUpRequest.getCin()
							// encoder.encode(signUpRequest.getPassword()));
public Utilisateur(String nom, String prenom, String telephone, String email, String cin, String password, String QrCodeImagePath){
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.cin = cin;
    this.password = password;
    this.telephone = telephone;
    this.codeQrImagePath = QrCodeImagePath;
}

public Utilisateur(String nom, String prenom, String telephone, String email, String cin, String password){
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.cin = cin;
    this.password = password;
    this.telephone = telephone;
   
}

    


}
