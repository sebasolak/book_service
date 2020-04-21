package com.example.book_service.service;

import com.example.book_service.dao.TouristDao;
import com.example.book_service.model.Tourist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TouristService {

    private final TouristDao touristDao;

    @Autowired
    public TouristService(@Qualifier("touristDao") TouristDao touristDao) {
        this.touristDao = touristDao;
    }

    public List<Tourist> selectAllTourist(Optional<String> name,
                                          Optional<String> lastName) {
        List<Tourist> tourists = touristDao.selectAllTourist();
        if (!name.isPresent() && !lastName.isPresent()) {
            return tourists;
        }
        if(name.isPresent()&&lastName.isPresent()){
            return tourists.stream()
                    .filter(tourist -> tourist.getName().equalsIgnoreCase(name.get())&&
                            tourist.getLastName().equalsIgnoreCase(lastName.get()))
                    .collect(Collectors.toList());
        }
        return name.map(s -> tourists.stream()
                .filter(tourist ->
                        tourist.getName().equalsIgnoreCase(s))
                .collect(Collectors.toList())).orElseGet(() -> tourists.stream()
                .filter(tourist ->
                        tourist.getLastName().equalsIgnoreCase(lastName.get()))
                .collect(Collectors.toList()));
    }

    public int insertNewTourist(UUID touristId, Tourist newTourist) {
        UUID touristUid = touristId == null ? UUID.randomUUID() : touristId;
        newTourist.setTouristId(touristUid);
        return touristDao.insertNewTourist(touristUid, newTourist);
    }

    public Tourist selectTouristById(UUID touristId) {
        return touristDao.selectTouristById(touristId);
    }

    public int updateTouristById(UUID touristId, Tourist update) {
        update.setTouristId(touristId);
        return touristDao.updateTouristById(touristId, update);
    }

    public int deleteTouristById(UUID touristId) {
        return touristDao.deleteTouristById(touristId);
    }
}
