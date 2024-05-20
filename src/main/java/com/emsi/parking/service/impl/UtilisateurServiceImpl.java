package com.emsi.parking.service.impl;

import com.emsi.parking.converter.impl.UtilisateurGetDtoConverter;
import com.emsi.parking.converter.impl.UtilisateurPostConverter;
import com.emsi.parking.dto.UtilisateurGetDto;
import com.emsi.parking.dto.UtilisateurPostDto;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import com.emsi.parking.service.PaiementService;
import com.emsi.parking.service.UtilisateurService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    final UtilisateurRepository utilisateurRepository;
    final UtilisateurPostConverter utilisateurPostConverter;
    final UtilisateurGetDtoConverter utilisateurGetDtoConverter;
    @Override
    public Utilisateur update(UtilisateurPostDto utilisateurPostDto) throws Exception{
    Utilisateur utilisateurFromDB = utilisateurRepository.findById(utilisateurPostDto.getId()).orElse(null);
    if(utilisateurFromDB == null) throw new Exception("User not found");
    utilisateurPostDto.setId(utilisateurFromDB.getId());
    return utilisateurRepository.save(utilisateurPostConverter.convertToDM(utilisateurPostDto));
    }

    @Override
    public List<UtilisateurGetDto> findAll() {
        return utilisateurGetDtoConverter.convertToDTOs(utilisateurRepository.findAll());
    }

    @Override
    public UtilisateurGetDto getUtilisateurById(long id) {
        return utilisateurGetDtoConverter.convertToDTO(utilisateurRepository.findById(id));

    }

    @Override
    public UtilisateurGetDto getUtilisateurByTelephone(String telephone) {
        return utilisateurGetDtoConverter.convertToDTO(utilisateurRepository.findByTelephone(telephone));
    }

}
