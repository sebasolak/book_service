package com.example.book_service.dao;

import com.example.book_service.model.Flight;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlightDao {

    List<Flight> selectAllFlights();

    void insertNewFlight(UUID flightId, Flight newFlight);

    Optional<Flight> selectFlightById(UUID flightId);

    void updateFlightById(UUID flightId, Flight update);

    void deleteFlightById(UUID flightId);
}
