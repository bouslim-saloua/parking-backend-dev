/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.service.impl;

import com.emsi.parking.model.Secteur;
import com.emsi.parking.repository.SecteurRepository;
import com.emsi.parking.service.SecteurService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author bssal
 */
@RequiredArgsConstructor
@Service
public class SecteurServiceImpl implements SecteurService {
    
    final SecteurRepository secteurRepository;

    @Override
    public List<Secteur> getAll() {
        return secteurRepository.findAll();
    }
    
}
