package com.emsi.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emsi.parking.model.Paiement;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}
