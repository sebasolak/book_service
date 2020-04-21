package com.example.book_service.model;

import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
public class Flight {

    private UUID flightId;
    private String destination;

    public Flight(UUID flightId,String destination) {
        this.flightId=flightId;
        this.destination = destination;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public String getDestination() {
        return destination;
    }
}
