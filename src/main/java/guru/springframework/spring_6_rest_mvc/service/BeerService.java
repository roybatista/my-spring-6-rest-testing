package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.model.Beer;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BeerService {
    Beer getBeerById(UUID id);
    List<Beer> getAllBeers();
    Beer createBeer(Beer beer);

    void updateBeer(UUID id, Beer beer);

    void removeBeer(UUID id);
}
