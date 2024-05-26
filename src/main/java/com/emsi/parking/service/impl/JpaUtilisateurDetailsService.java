/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.service.impl;

import com.emsi.parking.model.CustomUtilisateurDetails;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.repository.UtilisateurRepository;
import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author bssal
 */
@RequiredArgsConstructor
@Service
public class JpaUtilisateurDetailsService implements UserDetailsService{
    
final UtilisateurRepository utilisateurRepository;

@Override
public CustomUtilisateurDetails loadUserByUsername(String username){
    Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("Problem during authentication!");

    Utilisateur utilisateur = utilisateurRepository.findByEmail(username).orElseThrow(s);
return CustomUtilisateurDetails.build(utilisateur);
}
}
