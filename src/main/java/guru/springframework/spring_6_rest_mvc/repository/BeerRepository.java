package guru.springframework.spring_6_rest_mvc.repository;

import guru.springframework.spring_6_rest_mvc.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
