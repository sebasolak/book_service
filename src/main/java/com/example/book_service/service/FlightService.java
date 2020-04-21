package com.example.book_service.service;

import com.example.book_service.dao.FlightDao;
import com.example.book_service.model.Flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlightService {

    private final FlightDao flightDao;

    @Autowired
    public FlightService(@Qualifier("flightDao") FlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> selectAllFlights() {
        return flightDao.selectAllFlights();
    }

    public void insertNewFlight(UUID flightId, Flight newFlight) {
        UUID flightUid = flightId == null ? UUID.randomUUID() : flightId;
        newFlight.setFlightId(flightUid);
        flightDao.insertNewFlight(flightUid,newFlight);
    }

    public Optional<Flight>selectFlightById(UUID flightId){
        return flightDao.selectFlightById(flightId);
    }

    public void updateFlightById(UUID flightId,Flight update){
        Optional<Flight>optionalFlight=selectFlightById(flightId);
        if(optionalFlight.isPresent()){
            update.setFlightId(flightId);
            flightDao.updateFlightById(flightId,update);
        }

        throw new NotFoundException("flight " + flightId + " not found");
    }

    public void deleteFlightById(UUID flightId){
        flightDao.deleteFlightById(flightId);
    }
}
