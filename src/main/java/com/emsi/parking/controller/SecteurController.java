/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.controller;

import com.emsi.parking.service.SecteurService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bssal
 */
@Api("Cette end point permet de gérer les secteurs")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/secteur")
public class SecteurController {
    
    final SecteurService secteurService;
    
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(secteurService.getAll());
    }
    
}
