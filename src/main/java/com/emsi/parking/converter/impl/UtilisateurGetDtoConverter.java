/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.converter.impl;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.dto.UtilisateurGetDto;
import com.emsi.parking.converter.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author bssal
 */
@Component
public class UtilisateurGetDtoConverter extends AbstractConverter<Utilisateur, UtilisateurGetDto>{
    private final ModelMapper modelMapper;

public UtilisateurGetDtoConverter(ModelMapper modelMapper){
modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
this.modelMapper = modelMapper;
}

@Override
public Utilisateur convertToDM(UtilisateurGetDto utilisateurGetDto){
return modelMapper.map(utilisateurGetDto, Utilisateur.class);
}

@Override
public UtilisateurGetDto convertToDTO(Utilisateur utilisateur){
return modelMapper.map(utilisateur, UtilisateurGetDto.class);
}
}
