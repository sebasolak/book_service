package com.example.book_service.dao;

import com.example.book_service.model.Flight;
import com.example.book_service.model.Tourist;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.example.book_service.dao.FakeFlightDao.*;

@Repository("touristDao")
public class FakeTouristDao implements TouristDao {

    private final Map<UUID, Tourist> touristDB;
//    static Flight f = null;
//
//    static {
//        for (Map.Entry<UUID, Flight> pair : getFlightDb().entrySet()) {
//            f = new Flight(pair.getKey(), pair.getValue().getDestination());
//            break;
//        }
//    }


    public FakeTouristDao() {
        touristDB = new HashMap<>();
        UUID tourist1 = UUID.randomUUID();
        UUID tourist2 = UUID.randomUUID();
        UUID tourist3 = UUID.randomUUID();
        UUID tourist4 = UUID.randomUUID();
        UUID tourist5 = UUID.randomUUID();
        UUID tourist6 = UUID.randomUUID();

        Flight tokyo = new Flight(UUID.randomUUID(), "Tokyo");
        Flight beijing = new Flight(UUID.randomUUID(), "Beijing");
        Flight seoul = new Flight(UUID.randomUUID(), "Seoul");
        Flight heraklion = new Flight(UUID.randomUUID(), "Heraklion");
        Flight hongKong = new Flight(UUID.randomUUID(), "Hong Kong");

        touristDB.put(tourist1,
                new Tourist(tourist1, "Jordan", "Howard",
                        tokyo)
        );
        touristDB.put(tourist2,
                new Tourist(tourist2, "Kristin", "Kelly",
                        beijing)
        );
        touristDB.put(tourist3,
                new Tourist(tourist3, "Nellie", "Clark",
                        seoul)
        );
        touristDB.put(tourist4,
                new Tourist(tourist4, "Dawn", "Sanchez",
                        heraklion)
        );
        touristDB.put(tourist5,
                new Tourist(tourist5, "Gabriella", "Jackson",
                        hongKong)
        );
//        touristDB.put(tourist6,
//                new Tourist(tourist6, "Joe", "Rose",
//                        f)
//        );


    }

    @Override
    public List<Tourist> selectAllTourist() {
        return new ArrayList<>(touristDB.values());
    }

    @Override
    public int insertNewTourist(UUID touristId, Tourist newTourist) {
        UUID touristUid = touristId == null ? UUID.randomUUID() : touristId;
        newTourist.setTouristId(touristUid);
        touristDB.put(touristUid, newTourist);
        return 1;
    }

    @Override
    public Tourist selectTouristById(UUID touristId) {
        return touristDB.get(touristId);
    }

    @Override
    public int updateTouristById(UUID touristId, Tourist update) {
        update.setTouristId(touristId);
        touristDB.put(touristId, update);
        return 1;
    }

    @Override
    public int deleteTouristById(UUID touristId) {

        touristDB.remove(touristId);
        return 1;
    }
}
