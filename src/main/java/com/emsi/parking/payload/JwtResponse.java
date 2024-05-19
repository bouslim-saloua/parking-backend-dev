/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.payload;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bssal
 */
@Getter
@Setter
public class JwtResponse {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String qrCode;
    private String cin;
    private String qrCodeImage;
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse(long id, String nom, String prenom, String email, String telephone, String qrCode, String cin, String qrCodeImage, String token) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.qrCode = qrCode;
        this.cin = cin;
        this.qrCodeImage = qrCodeImage;
        this.token = token;
    }
    
    
}