package com.example.book_service.dao;

import com.example.book_service.model.Tourist;

import java.util.List;
import java.util.UUID;

public interface TouristDao {

    List<Tourist> selectAllTourist();

    int insertNewTourist(UUID touristId, Tourist newTourist);

    Tourist selectTouristById(UUID touristId);

    int updateTouristById(UUID touristId, Tourist update);

    int deleteTouristById(UUID touristId);
}
