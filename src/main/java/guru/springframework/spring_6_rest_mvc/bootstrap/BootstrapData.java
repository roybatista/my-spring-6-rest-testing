package guru.springframework.spring_6_rest_mvc.bootstrap;

import guru.springframework.spring_6_rest_mvc.entity.Beer;
import guru.springframework.spring_6_rest_mvc.entity.Customer;
import guru.springframework.spring_6_rest_mvc.model.BeerStyle;
import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import guru.springframework.spring_6_rest_mvc.repository.BeerRepository;
import guru.springframework.spring_6_rest_mvc.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeers();
        loadCustomers();
    }

    private void loadCustomers() {
        if(customerRepository.count() == 0) {

            Customer customer01 = Customer.builder()
                    .name("Customer 01").build();
            Customer customer02 = Customer.builder()
                    .name("Customer 02").build();
            Customer customer03 = Customer.builder()
                    .name("Customer 03").build();
            Customer customer04 = Customer.builder()
                    .name("Customer 04").build();

            customerRepository.save(customer01);
            customerRepository.save(customer02);
            customerRepository.save(customer03);
            customerRepository.save(customer04);
        }
    }

    private void loadBeers(){
        if (beerRepository.count() == 0){
            Beer beer1 = Beer.builder()
                    .beerName("Galaxy Cat")
                    .bearStyle(BeerStyle.PALE_ALE)
                    .upc("12356")
                    .price(new BigDecimal("12.99"))
                    .quantityOnHand(122)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Beer beer2 = Beer.builder()
                    .beerName("Crank")
                    .bearStyle(BeerStyle.PALE_ALE)
                    .upc("12356222")
                    .price(new BigDecimal("11.99"))
                    .quantityOnHand(392)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            Beer beer3 = Beer.builder()
                    .beerName("Sunshine City")
                    .bearStyle(BeerStyle.IPA)
                    .upc("12356")
                    .price(new BigDecimal("13.99"))
                    .quantityOnHand(144)
                    .createDate(LocalDateTime.now())
                    .updateDate(LocalDateTime.now())
                    .build();

            beerRepository.save(beer1);
            beerRepository.save(beer2);
            beerRepository.save(beer3);
        }
    }
}
