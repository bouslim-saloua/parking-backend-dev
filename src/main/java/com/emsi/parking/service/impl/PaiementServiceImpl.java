package com.emsi.parking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsi.parking.model.Paiement;
import com.emsi.parking.repository.PaiementRepository;
import com.emsi.parking.service.PaiementService;


@Service
public class PaiementServiceImpl implements PaiementService{
	@Autowired
	private PaiementRepository paiementRepository;

	@Override
	public Paiement save(Paiement p) {
		return paiementRepository.save(p);
	}

	@Override
	public void delete(Paiement p) {
		paiementRepository.delete(p);
	}

	@Override
	public List<Paiement> findAll() {
		return paiementRepository.findAll();
	}

	@Override
	public Optional<Paiement> findById(Long id) {
		return paiementRepository.findById(id);

	}

	@Override
	public void deleteById(Long id) {
		paiementRepository.deleteById(id);
		
	}

}
