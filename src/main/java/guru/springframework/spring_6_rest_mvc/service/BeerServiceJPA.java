package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.entity.Beer;
import guru.springframework.spring_6_rest_mvc.mapper.BeerMapper;
import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import guru.springframework.spring_6_rest_mvc.repository.BeerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;


    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {

        return Optional.ofNullable(
                beerMapper.beeToBeerDTO(
                        beerRepository.findById(id).orElse(null)
                )
        );
    }

    @Override
    public List<BeerDTO> getAllBeers() {

        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beeToBeerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BeerDTO createBeer(BeerDTO beer) {
        return null;
    }

    @Override
    public void updateBeer(UUID id, BeerDTO beer) {

    }

    @Override
    public void removeBeer(UUID id) {
        Optional<Beer> beerEnity = beerRepository.findById(id);

        beerRepository.delete(beerEnity.orElseThrow());

    }
}
