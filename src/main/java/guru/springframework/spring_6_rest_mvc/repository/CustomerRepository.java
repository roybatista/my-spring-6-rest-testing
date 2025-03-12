package guru.springframework.spring_6_rest_mvc.repository;

import guru.springframework.spring_6_rest_mvc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
