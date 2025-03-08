package guru.springframework.spring_6_rest_mvc.mapper;

import guru.springframework.spring_6_rest_mvc.entity.Customer;
import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);
    
    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
