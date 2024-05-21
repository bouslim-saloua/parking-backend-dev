package com.emsi.parking.controller;


import com.emsi.parking.dto.UtilisateurGetDto;
import com.emsi.parking.service.UtilisateurService;
import com.google.zxing.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UtilisateurController {

    final UtilisateurService utilisateurService;
    
    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService){
        this.utilisateurService = utilisateurService;
    }
    
  /*  @PutMapping("/")
public ResponseEntity<?> update( @RequestBody UtilisateurPostDto utilisateurPostDto)throws Exception{
if(utilisateurPostDto == null) 
return ResponseEntity.badRequest().body("L'utilisateur fourni n'est pas valid");
return ResponseEntity.ok().body(UtilisateurGetDtoConverter.convertToDTO(utilisateurService.update(utilisateurPostDto)));
}   */
    
    @GetMapping("/")
    public ResponseEntity<List<UtilisateurGetDto>> findAll(){
    return ResponseEntity.ok().body(utilisateurService.findAll());
    }
  
    @GetMapping("/telephone/{telephone}")
    public ResponseEntity<?> getUtilisateurByTelephone(@PathVariable String telephone){
    return ResponseEntity.ok().body(utilisateurService.getUtilisateurByTelephone(telephone));
    }
    
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getUtilisateurById(@PathVariable long id){
        return ResponseEntity.ok().body(utilisateurService.getUtilisateurById(id));
    }
    
    
    @PostMapping("/read-qr")
    public UtilisateurGetDto readQRCode(@RequestParam("file") MultipartFile file) throws NotFoundException {
        return utilisateurService.readQRCode(file);
    }
}
