package com.example.book_service.resource;

import com.example.book_service.model.Tourist;
import com.example.book_service.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/tourist")
public class TouristResource {

    private final TouristService touristService;

    @Autowired
    public TouristResource(TouristService touristService) {
        this.touristService = touristService;
    }

    @GetMapping
    public List<Tourist> getAllTourist(@QueryParam("name") String name,
                                       @QueryParam("lastName") String lastName) {
        return touristService.selectAllTourist(Optional.ofNullable(name),
                Optional.ofNullable(lastName));
    }

    @PostMapping
    public void addNewTourist(@RequestBody Tourist newTourist) {
        touristService.insertNewTourist(UUID.randomUUID(), newTourist);
    }

    @GetMapping(path = "{touristId}")
    public Tourist getTouristById(@PathVariable UUID touristId) {
        return touristService.selectTouristById(touristId);
    }

    @PutMapping(path = "{touristId}")
    public void updateTouristById(@PathVariable UUID touristId,
                                  @RequestBody Tourist update) {
        touristService.updateTouristById(touristId, update);
    }

    @DeleteMapping(path = "{touristId}")
    public void deleteTouristById(@PathVariable UUID touristId) {
        touristService.deleteTouristById(touristId);
    }
}
