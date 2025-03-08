package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import guru.springframework.spring_6_rest_mvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    Map<UUID, BeerDTO> beers;

    public BeerServiceImpl() {
        this.beers = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Dog")
                .bearStyle(BeerStyle.ALE)
                .upc("954321")
                .price(new BigDecimal("9.99"))
                .quantityOnHand(150)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Duck")
                .bearStyle(BeerStyle.PILSNER)
                .upc("854762")
                .price(new BigDecimal("4.99"))
                .quantityOnHand(135)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .bearStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beers.put(beer1.getId(), beer1);
        beers.put(beer2.getId(), beer2);
        beers.put(beer3.getId(), beer3);
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        log.debug("Get Beer ID in service was called");

        return Optional.of(beers.get(id));
    }

    @Override
    public List<BeerDTO> getAllBeers(){
        return new ArrayList<>(beers.values());
    }

    @Override
    public BeerDTO createBeer(BeerDTO beer) {

        BeerDTO newBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .version(1)
                .beerName(beer.getBeerName())
                .bearStyle(beer.getBearStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .build();
        beers.put(newBeer.getId(), newBeer);

        return newBeer;
    }

    @Override
    public void updateBeer(UUID id, BeerDTO beer) {
        BeerDTO existingBeer = beers.get(id);

       existingBeer.setBeerName(beer.getBeerName());
       existingBeer.setPrice(beer.getPrice());
       existingBeer.setUpdateDate(LocalDateTime.now());

       beers.put(existingBeer.getId(), existingBeer);
    }

    @Override
    public void removeBeer(UUID id) {
        beers.remove(id);
    }
}
