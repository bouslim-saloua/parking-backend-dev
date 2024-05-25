/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author bssal
 */
@Getter
@Setter
public class UtilisateurGetDto {


@JsonProperty("id")
private Long id;


@JsonProperty("nom")
    private String nom;

@JsonProperty("prenom")
private String prenom;

@Email
@JsonProperty("email")
private String email;

@JsonProperty("telephone")
private String telephone;

@JsonProperty("codeQrImagePath")
private String qrCodeImage;
}