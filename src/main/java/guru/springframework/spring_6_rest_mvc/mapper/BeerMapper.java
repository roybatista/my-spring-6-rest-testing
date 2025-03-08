package guru.springframework.spring_6_rest_mvc.mapper;

import guru.springframework.spring_6_rest_mvc.entity.Beer;
import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDTOtoBeer(BeerDTO bearDTO);

    BeerDTO beeToBeerDTO(Beer beer);
}
