package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    Map<UUID,Customer> customerMap = new HashMap<>();

    public CustomerServiceImpl() {

        Customer customer1 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Customer customer2 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Customer customer3 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Customer customer4 = Customer.builder()
                .id(UUID.randomUUID())
                .name("Customer 4")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);
        customerMap.put(customer4.getId(), customer4);
    }

    @Override
    public Customer getCustomerByID(UUID id) {
        return customerMap.get(id);
    }

    @Override
    public List<Customer> getCustomers() {

        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer addCustomer(Customer customer) {
        Customer newCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .name(customer.getName())
                .version(1)
                .build();
        customerMap.put(newCustomer.getId(), newCustomer);
        return newCustomer;
    }

    @Override
    public void updateCustomer(UUID id, Customer customer) {
        Customer existingCustomer = customerMap.get(id);

        existingCustomer.setName(customer.getName());

        customerMap.put(existingCustomer.getId(), existingCustomer);

    }

    @Override
    public void removeBeer(UUID id) {
        customerMap.remove(id);
    }

    @Override
    public void patch(UUID id, Customer customer) {

        Customer existingCustomer =customerMap.get(id);

        if(StringUtils.hasText(customer.getName())){
            existingCustomer.setName(customer.getName());
        }

    }


}
