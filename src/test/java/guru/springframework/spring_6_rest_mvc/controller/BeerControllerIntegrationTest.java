package guru.springframework.spring_6_rest_mvc.controller;

import guru.springframework.spring_6_rest_mvc.entity.Beer;
import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import guru.springframework.spring_6_rest_mvc.repository.BeerRepository;
import guru.springframework.spring_6_rest_mvc.service.BeerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerControllerIntegrationTest {

    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeers() {
        assertThat(beerController.getBeers().size()).isEqualTo(3);
    }

    @Transactional
    @Rollback
    @Test
    void testEmptyList() {
        beerRepository.deleteAll();;

        assertThat(beerController.getBeers().size()).isEqualTo(0);
    }

    @Test
    void testGetBeerByIdNull() {

        assertThrows(NotFoundException.class,()-> {
            BeerDTO beerDTO = beerController.getBeerByID(UUID.randomUUID());
        });

    }

    @Test
    void testGetBeerById() {

        Beer beer = beerRepository.findAll().getFirst();

        BeerDTO beerDTO = beerController.getBeerByID(beer.getId());

         assertThat(beerDTO).isNotNull();

    }
}