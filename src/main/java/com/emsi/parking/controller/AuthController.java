/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.controller;

import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.payload.JwtResponse;
import com.emsi.parking.payload.LoginRequest;
import com.emsi.parking.payload.RegisterRequest;
import com.emsi.parking.repository.UtilisateurRepository;
import com.emsi.parking.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
/**
 *
 * @author bssal
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    /*@Autowired
    private UtilisateurService utilisateurService;*/

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest) {
        if (utilisateurRepository.existsByEmail(registerRequest.getEmail())) {
            return "Email is already taken!";
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(registerRequest.getNom());
        utilisateur.setPrenom(registerRequest.getPrenom());
        utilisateur.setEmail(registerRequest.getEmail());
        utilisateur.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        utilisateur.setCin(registerRequest.getCin());
        utilisateur.setCodeQr(registerRequest.getCodeQr());
        utilisateur.setCodeQrImagePath(registerRequest.getCodeQrImagePath());
        utilisateur.setTelephone(registerRequest.getTelephone());

        utilisateurRepository.save(utilisateur);

        return "User registered successfully";
    }

    @PostMapping("/login")
    public JwtResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String jwt = jwtTokenProvider.generateToken(authentication);

        return new JwtResponse(jwt);
    }
}
