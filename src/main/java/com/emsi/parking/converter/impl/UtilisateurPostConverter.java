/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.converter.impl;

import com.emsi.parking.converter.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.dto.UtilisateurPostDto;

/**
 *
 * @author bssal
 */

@Component
public class UtilisateurPostConverter extends AbstractConverter<Utilisateur, UtilisateurPostDto>{
    private final ModelMapper modelMapper;

public UtilisateurPostConverter(ModelMapper modelMapper){
        modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
this.modelMapper = modelMapper;
}

//From dto to model
@Override
public Utilisateur convertToDM(UtilisateurPostDto utilisateurPostDto){
return modelMapper.map(utilisateurPostDto, Utilisateur.class);
}

//From model to dto
@Override
public UtilisateurPostDto convertToDTO(Utilisateur utilisateur){
return modelMapper.map(utilisateur, UtilisateurPostDto.class);
}

}

