/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.emsi.parking.repository;

import com.emsi.parking.model.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author bssal
 */
public interface SecteurRepository extends JpaRepository<Secteur, Long>{
    
    
}
