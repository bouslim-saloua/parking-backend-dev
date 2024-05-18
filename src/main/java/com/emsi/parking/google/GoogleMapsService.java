/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emsi.parking.google;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author bssal
 */

@Service
public class GoogleMapsService {
    
    @Value("${google.maps.api.key}")
    private String googleApiKey;
    
    private final RestTemplate restTemplate;
    
    public GoogleMapsService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    
    public String findNearbyParking(String adresse){
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + adresse + "&radius=1500&type=parking&key=" + googleApiKey;
        return restTemplate.getForObject(url, String.class);
    }
}
