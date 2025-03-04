package guru.springframework.spring_6_rest_mvc.controller;

import guru.springframework.spring_6_rest_mvc.model.Customer;
import guru.springframework.spring_6_rest_mvc.service.CustomerService;
import guru.springframework.spring_6_rest_mvc.service.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer/{customerid}")
    public Customer getCustomerById(@PathVariable("customerid") UUID uuid) {

        return customerService.getCustomerByID(uuid);

    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }


    @PostMapping("/addcustomer")
    public ResponseEntity addCustomer(@RequestBody Customer customer){

        Customer newCustomer = customerService.addCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("location","/api/v1/customer/"+newCustomer.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/customer/update/{customerID}")
    public ResponseEntity updateCustomer(@PathVariable("customerID") UUID id, @RequestBody Customer customer){

        customerService.updateCustomer(id, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/customer/{customerID}")
    public ResponseEntity deleteCustomer(@PathVariable("customerID") UUID id) {

        customerService.removeBeer(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PatchMapping("/customer/patch/{customerID}")
    public ResponseEntity updatePatchBeyID(@PathVariable("customerID") UUID id, @RequestBody Customer customer){

        customerService.patch(id, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
