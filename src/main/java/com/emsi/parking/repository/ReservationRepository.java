package com.emsi.parking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {


	Long countByDateReservation(Date date);

	//List<Reservation> findByPlaceIdAndDateEntreeAndDateSortieAndHeureEntreeAndHeureSortieBetween(Long id,
	//		Date dateEntree, Date dateSortie, int heureEntree, int heureSortie);

}
