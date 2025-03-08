package guru.springframework.spring_6_rest_mvc.controller;

import guru.springframework.spring_6_rest_mvc.model.CustomerDTO;
import guru.springframework.spring_6_rest_mvc.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/api/v1/customers")
    public List<CustomerDTO> getCustomers(){
        return customerService.getAllCustomers();
    }


}
