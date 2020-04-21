package com.example.book_service.model;


import com.example.book_service.dao.FakeFlightDao;
import com.example.book_service.service.FlightService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class Tourist {

//    private UUID userId;

    @Autowired
    private FlightService flightService;

    private UUID touristId;
    private String name;
    private String lastName;
    private List<Flight> flights;


    public Tourist(UUID touristId, String name, String lastName, Flight flight) {
        this.touristId = touristId;
        this.name = name;
        this.lastName = lastName;
        flights = new ArrayList<>();
        flights.add(flight);
    }

    public UUID getTouristId() {
        return touristId;
    }

    public void setTouristId(UUID touristId) {
        this.touristId = touristId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}
