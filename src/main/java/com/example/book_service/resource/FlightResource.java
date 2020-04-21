package com.example.book_service.resource;

import com.example.book_service.model.Flight;
import com.example.book_service.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/flight")
public class FlightResource {

    private final FlightService flightService;

    @Autowired
    public FlightResource(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.selectAllFlights();
    }

    @PostMapping
    public void addNewFlight(@RequestBody Flight newFlight) {
        flightService.insertNewFlight(UUID.randomUUID(), newFlight);
    }

    @GetMapping(path = "{flightId}")
    public Flight getFlightById(@PathVariable UUID flightId) {
        return flightService
                .selectFlightById(flightId)
                .orElseThrow(() -> new NotFoundException("flight " + flightId + " not found"));
    }

    @PutMapping(path = "{flightId}")
    public void updateFlightById(@PathVariable UUID flightId,
                                 @RequestBody Flight update) {
        flightService.updateFlightById(flightId,update);
    }

    @DeleteMapping(path = "{flightId}")
    public void deleteFlightById(@PathVariable UUID flightId) {
        flightService.deleteFlightById(flightId);
    }


}
