package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    Customer getCustomerByID(UUID id);
    List<Customer> getCustomers();
    Customer addCustomer(Customer customer);

    void updateCustomer(UUID id, Customer customer);

    void removeBeer(UUID id);

    void patch(UUID id, Customer customer);
}
