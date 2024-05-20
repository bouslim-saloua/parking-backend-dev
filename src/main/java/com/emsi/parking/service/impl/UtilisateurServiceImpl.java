package com.emsi.parking.service.impl;

import com.emsi.parking.config.QRCodeReader;
import com.emsi.parking.converter.impl.UtilisateurGetDtoConverter;
import com.emsi.parking.converter.impl.UtilisateurPostConverter;
import com.emsi.parking.dto.UtilisateurGetDto;
import com.emsi.parking.dto.UtilisateurPostDto;
import com.emsi.parking.exception.UserNotFoundException;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import com.emsi.parking.service.UtilisateurService;
import com.google.zxing.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
    
     @Override
    public UtilisateurGetDto getUtilisateurByQRCode(String decodedText) {
    Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(decodedText);
    
    if (utilisateurOptional.isPresent()) {
        Utilisateur utilisateur = utilisateurOptional.get();
        return utilisateurGetDtoConverter.convertToDTO(utilisateur);
    } else {
        // Handle the case where the user is not found, e.g., throw an exception or return null
        throw new UserNotFoundException("User not found with email: " + decodedText);
    }
    }

    @Override
    public UtilisateurGetDto readQRCode(MultipartFile file) throws NotFoundException {
        try {
            // Sauvegarder temporairement le fichier uploadé
            Path tempFile = Files.createTempFile("qr_", file.getOriginalFilename());
            Files.write(tempFile, file.getBytes());

            // Lire le QR code à partir du fichier temporaire
            String decodedText = QRCodeReader.readQRCode(tempFile.toString());
            Files.delete(tempFile); // Supprimer le fichier temporaire après lecture

            // Récupérer l'utilisateur en utilisant le texte décodé
         /*   Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(decodedText);
             if (utilisateurOptional.isPresent()) {
        Utilisateur utilisateur = utilisateurOptional.get();
        return utilisateurGetDtoConverter.convertToDTO(utilisateur);
    } else {
        // Handle the case where the user is not found, e.g., throw an exception or return null
        throw new UserNotFoundException("User not found with email: " + decodedText);
    }*/

           // return utilisateurGetDtoConverter.convertToDTO(utilisateurRepository.findByEmail(decodedText));
            return getUtilisateurByQRCode(decodedText);
        } catch (IOException | NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
   
}


