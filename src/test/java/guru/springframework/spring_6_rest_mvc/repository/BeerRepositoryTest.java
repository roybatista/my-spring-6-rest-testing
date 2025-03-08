package guru.springframework.spring_6_rest_mvc.repository;

import guru.springframework.spring_6_rest_mvc.entity.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testNewBeer() {
        Beer beer = beerRepository.save(Beer.builder()
                        .beerName("New Beer")
                        .price(new BigDecimal("9.99"))
                .build());

        assertThat(beer.getBeerName()).isNotNull();
        assertThat(beer.getPrice()).isNotNull();
        assertThat(beer.getId()).isNotNull();
        assertThat(beer.getPrice()).isEqualTo("9.99");

        }
    }
