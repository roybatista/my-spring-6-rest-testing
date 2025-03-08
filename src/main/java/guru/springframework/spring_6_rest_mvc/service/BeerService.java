package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDTO> getBeerById(UUID id);

    List<BeerDTO> getAllBeers();

    BeerDTO createBeer(BeerDTO beer);

    void updateBeer(UUID id, BeerDTO beer);

    void removeBeer(UUID id);
}
