package guru.springframework.spring_6_rest_mvc.controller;


import com.sun.net.httpserver.Headers;
import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import guru.springframework.spring_6_rest_mvc.model.ResponseCustomer;
import guru.springframework.spring_6_rest_mvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
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
    public ResponseEntity addCustomer(@RequestBody CustomerDTO customerDTO){

        CustomerDTO newCustomer = customerService.saveNewCustomer(customerDTO);

        Headers headers = new Headers();
        headers.add("location", URL_CUSTOMER+"/"+newCustomer.getId());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

//        ResponseCustomer responseCustomer = ResponseCustomer.builder()
//                .status("200")
//                .discription("Customer Created")
//                .customer(newCustomer)
//                .build();
//
//        return new ResponseEntity<ResponseCustomer>(responseCustomer, HttpStatus.CREATED);

    }

    @DeleteMapping(URL_CUSTOMER_ID)
    public ResponseEntity deleteCustomer(@PathVariable("customerID") UUID customerID) {
        customerService.deleteCustomerById(customerID);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(URL_CUSTOMER_ID)
    public void updateCustomer(@PathVariable("customerID") UUID customerID, @RequestBody CustomerDTO customerDTO){
        System.out.println("customerID: " + customerID + ", CustomerDTO: " +customerDTO);
        customerService.updateCustomerById(customerID, customerDTO);
    }

    @PatchMapping(URL_CUSTOMER_ID)
    public void patchCustomer(@PathVariable("customerID") UUID customerID, @RequestBody CustomerDTO customerDTO){
        customerService.patchCustomerById(customerID, customerDTO);
    }
}
