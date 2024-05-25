/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.controller;

import com.emsi.parking.config.QRCodeGenerator;
import com.emsi.parking.model.CustomUtilisateurDetails;
import com.emsi.parking.model.ERole;
import com.emsi.parking.model.Role;
import com.emsi.parking.model.Utilisateur;
import com.emsi.parking.payload.request.LoginRequest;
import com.emsi.parking.payload.request.SignupRequest;
import com.emsi.parking.payload.response.JwtResponse;
import com.emsi.parking.payload.response.MessageResponse;
import com.emsi.parking.repository.RoleRepository;
import com.emsi.parking.repository.UtilisateurRepository;
import com.emsi.parking.security.JwtUtils;
import com.google.zxing.WriterException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
//@CrossOrigin("http://localhost:3000/")
@CrossOrigin(origins={"http://localhost:3000/","http://localhost:5000/"})
@RestController
@RequestMapping("/api/user/auth")
public class UserAuthController {
@Autowired
UtilisateurRepository userRepository;
     @Autowired
	       AuthenticationManager authenticationManager;
	
	@Autowired
	       RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	       JwtUtils jwtUtils;


//signIn

@PostMapping("/signIn")
	public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest loginRequest) {
            Optional<Utilisateur> user = userRepository.findByEmail(loginRequest.getEmail());

            if(!(userRepository.existsByEmail(loginRequest.getEmail()))){
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email or password don't match!"));

		}else if (user.isPresent()) {
			Utilisateur utilisateur = user.get();
			if (!(encoder.matches(loginRequest.getPassword(), utilisateur.getPassword()))) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Password doesn't match!"));
			}

		}
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		  CustomUtilisateurDetails userDetails = (CustomUtilisateurDetails) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
                //public JwtResponse(String accessToken, Long id, String email, String nom, String prenom,String telephone ,String cin, String qrCodeImage, List<String> roles) {
return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(),
userDetails.getNom(), userDetails.getPrenom(), userDetails.getTelephone(), userDetails.getCin(), userDetails.getQrCodePath(),roles));
	}

@PostMapping("/signup")
	public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signUpRequest) throws WriterException {
		//Error when email is already in use
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

            //Error when phone is already in use!
if( userRepository.existsByTelephone(signUpRequest.getTelephone())){
return ResponseEntity.badRequest().body(new MessageResponse("Erorr: N° Telephone est déjà utilisé!"));
}
if( userRepository.existsByCin(signUpRequest.getCin())){
return ResponseEntity.badRequest().body(new MessageResponse("Erorr: CIN est déjà utilisé!"));
}

 try {
           String qrCode = signUpRequest.getEmail()+"_"+System.currentTimeMillis();
            String qrCodePath = "qr_codes/" + signUpRequest.getEmail() + "_" + System.currentTimeMillis() + ".png";
           QRCodeGenerator.generateQRCodeImage(signUpRequest.getEmail(), 250, 250, qrCodePath);
		// Create new demandeur's account
		  Utilisateur utilisateur = new Utilisateur( signUpRequest.getNom(), signUpRequest.getPrenom(),signUpRequest.getTelephone(), signUpRequest.getEmail(),signUpRequest.getCin()
							 ,encoder.encode(signUpRequest.getPassword()), qrCodePath);
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER);//.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				
					Role userRole = roleRepository.findByName(ERole.ROLE_USER);//.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				
			});
		}
		utilisateur.setRoles(roles);
		userRepository.save(utilisateur);
		return ResponseEntity.ok(new MessageResponse("Account registered successfully!"));
                } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            
         		return ResponseEntity.ok(new MessageResponse("ERROOOORRRR REGISTERING THIS USER!"));

        }
	}
}
