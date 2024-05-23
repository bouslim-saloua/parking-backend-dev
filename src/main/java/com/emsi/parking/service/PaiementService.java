package com.emsi.parking.service;

import java.util.List;
import java.util.Optional;

import com.emsi.parking.model.Paiement;

public interface PaiementService {

	Paiement save(Paiement p);

	void delete(Paiement p);

	List<Paiement> findAll();

	Optional<Paiement> findById(Long id);

	void deleteById(Long id);

}
