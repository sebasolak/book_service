package com.example.book_service.service;

import com.example.book_service.dao.FakeTouristDao;
import com.example.book_service.model.Flight;
import com.example.book_service.model.Tourist;
import com.google.common.collect.ImmutableList;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class TouristServiceTest {

    @Mock
    private FakeTouristDao fakeTouristDao;

    private TouristService touristService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        touristService = new TouristService(fakeTouristDao);
    }

    @Test
    public void shouldSelectAllTourist() {
        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        Tourist mabel = new Tourist(null, "Mabel", "Bates",
                jerusalem);

        ImmutableList<Tourist> tourists = new ImmutableList.Builder<Tourist>()
                .add(mabel)
                .build();
        given(fakeTouristDao.selectAllTourist()).willReturn(tourists);

        List<Tourist> allTourist = touristService.selectAllTourist(Optional.empty(), Optional.empty());
        assertThat(allTourist).hasSize(1);

        Tourist tourist = allTourist.get(0);

        assertThat(tourist).isEqualToComparingFieldByField(mabel);

//        given(fakeDataDao.selectAllUser()).willReturn(users);
//
//        List<User> allUsers = userService.getAllUser();
//
//        assertThat(allUsers).hasSize(1);

    }

    @Test
    public void shouldGetTouristByName() {

        Tourist mabel = new Tourist(UUID.randomUUID(), "Mabel", "Bates",
                new Flight(UUID.randomUUID(), "Jerusalem"));

        Tourist joe = new Tourist(UUID.randomUUID(), "Joe", "Snyder",
                new Flight(UUID.randomUUID(), "Montevideo"));

        Tourist eileen = new Tourist(UUID.randomUUID(), "Eileen", "Snyder",
                new Flight(UUID.randomUUID(), "Dubai"));

        ImmutableList<Tourist> tourists = new ImmutableList.Builder<Tourist>()
                .add(mabel)
                .add(joe)
                .add(eileen)
                .build();

        given(fakeTouristDao.selectAllTourist()).willReturn(tourists);

        assertThat(touristService
                .selectAllTourist(Optional.empty(),Optional.empty())).hasSize(3);

        List<Tourist> queryByName
                = touristService.selectAllTourist(Optional.of("Mabel"), Optional.empty());

        assertThat(queryByName).hasSize(1);

        List<Tourist> queryByLastName
                = touristService.selectAllTourist(Optional.empty(), Optional.of("Snyder"));

        assertThat(queryByLastName).hasSize(2);

        List<Tourist> queryByNameAndLastName
                = touristService.selectAllTourist(Optional.of("Eileen"), Optional.of("Snyder"));

        assertThat(queryByNameAndLastName).hasSize(1);

    }

    @Test
    public void insertNewTourist() {
        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);

        given(fakeTouristDao.insertNewTourist(any(UUID.class), any(Tourist.class)))
                .willReturn(1);

        ArgumentCaptor<Tourist> captor = ArgumentCaptor.forClass(Tourist.class);

        touristService.insertNewTourist(mabelId, mabel);

        verify(fakeTouristDao).insertNewTourist(eq(mabelId), captor.capture());

        Tourist tourist = captor.getValue();

        assertThat(tourist).isEqualToComparingFieldByField(mabel);
    }

    @Test
    public void shouldSelectTouristById() {
        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);

        given(fakeTouristDao.selectTouristById(mabelId))
                .willReturn(mabel);

        Tourist touristMaybe = touristService.selectTouristById(mabelId);

        assertThat(touristMaybe).isEqualToComparingFieldByField(mabel);
    }

    @Test
    public void shouldUpdateTouristById() {
        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);


        given(fakeTouristDao.selectTouristById(mabelId)).willReturn(mabel);
        given(fakeTouristDao.updateTouristById(mabelId, mabel)).willReturn(1);


        Flight marrakesh = new Flight(UUID.randomUUID(), "Marrakesh");
        Tourist update = new Tourist(mabelId, "Vincent", "Wright", marrakesh);
        fakeTouristDao.updateTouristById(mabelId, update);


        assertThat(update.getTouristId()).isEqualTo(mabelId);
        assertThat(update.getName()).isEqualTo("Vincent");
        assertThat(update.getLastName()).isEqualTo("Wright");
        assertThat(update.getFlights()).isEqualTo(Collections.singletonList(marrakesh));


    }

    @Test
    public void shouldDeleteTouristById() {

        Flight jerusalem = new Flight(UUID.randomUUID(), "Jerusalem");

        UUID mabelId = UUID.randomUUID();
        Tourist mabel = new Tourist(mabelId, "Mabel", "Bates",
                jerusalem);


        given(fakeTouristDao.selectTouristById(mabelId)).willReturn(mabel);
        given(fakeTouristDao.updateTouristById(mabelId, mabel)).willReturn(1);


        touristService.selectTouristById(mabelId);
        verify(fakeTouristDao).selectTouristById(mabelId);

        touristService.deleteTouristById(mabelId);
        verify(fakeTouristDao).deleteTouristById(mabelId);


    }
}