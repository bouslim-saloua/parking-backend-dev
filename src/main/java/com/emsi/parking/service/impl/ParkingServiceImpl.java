package com.emsi.parking.service.impl;

import com.emsi.parking.model.Parking;
import com.emsi.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import com.emsi.parking.service.ParkingService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ParkingServiceImpl implements ParkingService{

    final ParkingRepository parkingRepository;
    @Override
    public Parking ajouter(Parking parking) throws Exception{
        Parking parkingFromDB = parkingRepository.findById(parking.getId()).orElse(null);
         if(parkingFromDB != null) throw new Exception("parking with id " + parking.getId() + "already exists");
        return parkingRepository.save(parking);
    }

    @Override
    public Parking modifier(Parking parking) throws Exception{
        Parking parkingFromDB = parkingRepository.findById(parking.getId()).orElse(null);
        if(parkingFromDB == null) throw new Exception("parking with id " + parking.getId() + "doesn't exist");
        return parkingRepository.save(parking);
        
    }

    @Override
    public List<Parking> listeParkings() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking getParkingById(long id) throws Exception{
        Parking parkingFromDB = parkingRepository.findById(id).orElse(null);
        if(parkingFromDB == null) throw new Exception("Parking not found");
        return parkingFromDB;
    }

}
