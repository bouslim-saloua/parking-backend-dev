package com.emsi.parking.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Place implements Serializable{
/**
	 * 
	 */
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

        public int getNumero() {
         return this.numero;
         }

       public void setNumero(int numero) {
       this.numero = numero;
       }
       
       public boolean isReserve() {
           return this.reserve;
       }

       public void setReserve(boolean reserve) {
           this.reserve = reserve;
       }
 
       public void setParking(Parking parking) {
           this.parking = parking;
       }

       public Parking getParking() {
           return this.parking;
       }


}
