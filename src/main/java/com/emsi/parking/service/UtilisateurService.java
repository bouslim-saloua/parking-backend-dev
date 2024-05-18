package com.emsi.parking.service;

import com.emsi.parking.dto.UtilisateurGetDto;
import com.emsi.parking.dto.UtilisateurPostDto;
import com.emsi.parking.model.Utilisateur;
import java.util.List;

public interface UtilisateurService {
    
    Utilisateur update(UtilisateurPostDto utilisateurPostDto) throws Exception;
    List<UtilisateurGetDto> findAll();
    UtilisateurGetDto getUtilisateurById(long id);
    UtilisateurGetDto getUtilisateurByTelephone(String telephone);


}
