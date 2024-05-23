package com.emsi.parking.service;

import com.emsi.parking.dto.UtilisateurGetDto;
import com.emsi.parking.dto.UtilisateurPostDto;
import com.emsi.parking.model.Utilisateur;
import com.google.zxing.NotFoundException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UtilisateurService {
    
    Utilisateur update(UtilisateurPostDto utilisateurPostDto) throws Exception;
    List<UtilisateurGetDto> findAll();
    UtilisateurGetDto getUtilisateurById(long id);
    UtilisateurGetDto getUtilisateurByTelephone(String telephone);
    UtilisateurGetDto readQRCode(MultipartFile file) throws NotFoundException;
    UtilisateurGetDto getUtilisateurByQRCode(String decodedText);

}
