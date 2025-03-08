package guru.springframework.spring_6_rest_mvc.controller;

import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import guru.springframework.spring_6_rest_mvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private static final String URL_CUSTOMER = "/api/v1/customer";
    private static final String URL_CUSTOMER_ID = "/api/v1/customer/{customerID}";

    private final CustomerService customerService;

    @GetMapping(URL_CUSTOMER+"/customers")
    public List<CustomerDTO> getCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping(URL_CUSTOMER_ID)
    public Optional<CustomerDTO> getCustomer(@PathVariable("customerID") UUID customerID){
        return customerService.getCustomerById(customerID);
    }

    @PostMapping(URL_CUSTOMER)
    public CustomerDTO addCustomer(@RequestBody CustomerDTO customerDTO){
        return customerService.saveNewCustomer(customerDTO);
    }

    @DeleteMapping(URL_CUSTOMER_ID)
    public ResponseEntity deleteCustomer(@PathVariable("customerID") UUID customerID) {
        customerService.deleteCustomerById(customerID);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
