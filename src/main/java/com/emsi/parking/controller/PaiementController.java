package com.emsi.parking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emsi.parking.model.Paiement;
import com.emsi.parking.service.PaiementService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/paiement")
public class PaiementController {
	@Autowired
	private PaiementService paiementServices;

	
	@PostMapping( "/save")
	public Paiement save(@RequestBody Paiement p) {
		return paiementServices.save(p);
	}
    @PutMapping("/update")
	public Paiement update(@RequestBody Paiement p) {
		return paiementServices.save(p);
	}
    @DeleteMapping("/delete")
	public void delete(@RequestBody Paiement p) {
    	paiementServices.delete(p);
	}
	@GetMapping("")
	public List<Paiement> findAll() {
		return paiementServices.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Paiement> findById(@PathVariable("id") Long id) {
		return paiementServices.findById(id);
	}
	@DeleteMapping("/deletByid/{id}")
	public void deleteById(@PathVariable("id") Long id) {
		paiementServices.deleteById(id);
	}
}
