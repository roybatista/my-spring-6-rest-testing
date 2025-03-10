package guru.springframework.spring_6_rest_mvc.service;

import guru.springframework.spring_6_rest_mvc.mapper.CustomerMapper;
import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    HashMap<UUID, CustomerDTO> customerDTOHashMap;

    public CustomerServiceImpl() {
        this.customerDTOHashMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 1")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 2")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .name("Customer 3")
                .version(1)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        customerDTOHashMap.put(customer1.getId(), customer1);
        customerDTOHashMap.put(customer2.getId(), customer2);
        customerDTOHashMap.put(customer3.getId(), customer3);

    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID uuid) {

        return Optional.ofNullable((customerDTOHashMap.get(uuid)));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerDTOHashMap.values().stream().toList();
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {

        System.out.println("New Customer: " + customer);

        CustomerDTO newCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .name(customer.getName())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        System.out.println("New Customer: " + newCustomer);
        customerDTOHashMap.put(newCustomer.getId(), newCustomer);
        return newCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        customerDTOHashMap.put(customerId, customer);
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        customerDTOHashMap.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {

        CustomerDTO customerDTO = customerDTOHashMap.get(customerId);

        if(customerDTO != null) {
            if (customer.getId() != null && !customerDTO.getId().equals(customerId)) {
                customerDTO.setId(customer.getId());
            }
            if (customer.getName() != null && !customerDTO.getName().equals(customer.getName())) {
                customerDTO.setName(customer.getName());
            }
            if (customer.getVersion() != null && !customerDTO.getVersion().equals(customer.getVersion())) {
                customerDTO.setVersion(customer.getVersion());
            }
            if (customer.getCreatedDate() != null && !customerDTO.getCreatedDate().equals(customer.getCreatedDate())) {
                customerDTO.setCreatedDate(customer.getCreatedDate());
            }
            if (customer.getUpdateDate() != null && !customerDTO.getUpdateDate().equals(customer.getUpdateDate())) {
                customerDTO.setUpdateDate(customer.getUpdateDate());
            }
        }
    }

}
