/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.controller;

import com.emsi.parking.config.QRCodeGenerator;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.payload.JwtResponse;
import com.emsi.parking.payload.LoginRequest;
import com.emsi.parking.payload.RegisterRequest;
import com.emsi.parking.repository.UtilisateurRepository;
import com.emsi.parking.security.JwtTokenProvider;
import java.nio.file.Paths;
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
@RequestMapping("/api/user/auth")
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
    private final String storageDirectoryPath = Paths.get("uploaded-images").toAbsolutePath().toString();


    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest registerRequest) {
        if (utilisateurRepository.existsByEmail(registerRequest.getEmail())) {
            return "Email is already taken!";
        }
        if (utilisateurRepository.existsByTelephone(registerRequest.getTelephone())) {
            return "Phone number is already taken!";
        }
        if (utilisateurRepository.existsByCin(registerRequest.getCin())) {
            return "CIN is already taken!";
        }
        
        
        try {
           String qrCode = registerRequest.getEmail()+"_"+System.currentTimeMillis();
            String qrCodePath = "qr_codes/" + registerRequest.getEmail() + "_" + System.currentTimeMillis() + ".png";
           QRCodeGenerator.generateQRCodeImage(registerRequest.getEmail(), 250, 250, qrCodePath);
            // Further registration logic...
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(registerRequest.getNom());
        utilisateur.setPrenom(registerRequest.getPrenom());
        utilisateur.setEmail(registerRequest.getEmail());
        utilisateur.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        utilisateur.setCin(registerRequest.getCin());
        utilisateur.setCodeQrImagePath(qrCodePath);
        utilisateur.setCodeQr(qrCode);
        utilisateur.setTelephone(registerRequest.getTelephone());
        utilisateur.setRole("user");
         utilisateurRepository.save(utilisateur);
         
        return "User registered successfully";
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            
        return "Errroooor registring user";
        }

        // Enregistrer l'utilisateur dans la base de données (code DAO ou repository)
        // utilisateurRepository.save(utilisateur);  
       
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
