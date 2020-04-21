package com.example.book_service.dao;

import com.example.book_service.model.Flight;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("flightDao")
public class FakeFlightDao implements FlightDao {

    private final Map<UUID, Flight> flightDB;

    private static Map<UUID, Flight> FLIGHT_DB;

    public FakeFlightDao() {
        flightDB = new HashMap<>();

        UUID uid1 = UUID.randomUUID();
        UUID uid2 = UUID.randomUUID();
        UUID uid3 = UUID.randomUUID();
        UUID uid4 = UUID.randomUUID();
        UUID uid5 = UUID.randomUUID();

        Flight beijing = new Flight(uid1, "Beijing");
        flightDB.put(uid1, beijing);
        Flight hong_kong = new Flight(uid2, "Hong Kong");
        flightDB.put(uid2, hong_kong);
        Flight macau = new Flight(uid3, "Macau");
        flightDB.put(uid3, macau);
        Flight tokyo = new Flight(uid4, "Tokyo");
        flightDB.put(uid4, tokyo);
        Flight seoul = new Flight(uid5, "Seoul");
        flightDB.put(uid5, seoul);

        FLIGHT_DB = new HashMap<>();
        FLIGHT_DB.putAll(flightDB);

    }

    @Override
    public List<Flight> selectAllFlights() {
        return new ArrayList<>(flightDB.values());
    }

    @Override
    public void insertNewFlight(UUID flightId, Flight newFlight) {
        UUID flightUid = flightId == null ? UUID.randomUUID() : flightId;
        newFlight.setFlightId(flightUid);
        flightDB.put(flightUid, newFlight);
    }

    @Override
    public Optional<Flight> selectFlightById(UUID flightId) {
        return Optional.ofNullable(flightDB.get(flightId));
    }

    @Override
    public void updateFlightById(UUID flightId, Flight update) {
        update.setFlightId(flightId);
        flightDB.put(flightId, update);
    }

    @Override
    public void deleteFlightById(UUID flightId) {
        flightDB.remove(flightId);
    }

    //////////////

    public static Map<UUID, Flight> getFlightDb() {
        return FLIGHT_DB;
    }

    static Optional<Flight> selectFlightByIdStatic(UUID flightId) {
        return Optional.ofNullable(FLIGHT_DB.get(flightId));
    }

}
