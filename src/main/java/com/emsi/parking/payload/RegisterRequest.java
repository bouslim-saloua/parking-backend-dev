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
public class RegisterRequest {
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String cin;
    private String codeQr;
    private String codeQrImagePath;
    private String telephone;
}