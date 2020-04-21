package com.example.book_service.dao;

import com.example.book_service.model.Flight;
import com.example.book_service.model.Tourist;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class FakeTouristDaoTest {

    private FakeTouristDao fakeTouristDao;

    @Before
    public void setUp() throws Exception {
        fakeTouristDao = new FakeTouristDao();
    }

    @Test
    public void shouldSelectAllTourist() {
        List<Tourist> tourists = fakeTouristDao.selectAllTourist();
        assertThat(tourists).hasSize(5);
    }

    @Test
    public void insertNewTourist() {


        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);

        int size = fakeTouristDao.selectAllTourist().size();

        fakeTouristDao.insertNewTourist(mabelId, mabel);
        assertThat(fakeTouristDao.selectAllTourist()).hasSize(size + 1);

        Tourist tourist = fakeTouristDao.selectTouristById(mabelId);
        assertThat(tourist).isEqualToComparingFieldByField(mabel);


    }

    @Test
    public void shouldSelectTouristById() {

        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);

        fakeTouristDao.insertNewTourist(mabelId, mabel);

        Tourist tourist = fakeTouristDao.selectTouristById(mabelId);

        assertThat(tourist).isEqualToComparingFieldByField(mabel);

    }

    @Test
    public void shouldUpdateTouristById() {

        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);

        fakeTouristDao.insertNewTourist(mabelId, mabel);
        Flight marrakesh = new Flight(UUID.randomUUID(), "Marrakesh");

        Tourist update = new Tourist(mabelId, "Vincent", "Wright", marrakesh);
        fakeTouristDao.updateTouristById(mabelId, update);

        assertThat(update.getTouristId()).isEqualByComparingTo(mabel.getTouristId());

        assertThat(fakeTouristDao.selectTouristById(mabelId)).isEqualToComparingFieldByField(update);

        assertThat(fakeTouristDao.selectTouristById(mabelId).getFlights()).isNotSameAs(jerusalem);


    }

    @Test
    public void deleteTouristById() {

        UUID userToDelete=
                fakeTouristDao.selectAllTourist().get(0).getTouristId();
        assertThat(fakeTouristDao.selectTouristById(userToDelete)).isNotNull();

        fakeTouristDao.deleteTouristById(userToDelete);
        assertThat(fakeTouristDao.selectTouristById(userToDelete)).isNull();

    }
}