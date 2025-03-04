package guru.springframework.spring_6_rest_mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring_6_rest_mvc.model.Beer;
import guru.springframework.spring_6_rest_mvc.model.Customer;
import guru.springframework.spring_6_rest_mvc.service.CustomerService;
import guru.springframework.spring_6_rest_mvc.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> argumentUUIDCaptor;

    CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new CustomerServiceImpl();
    }

    @Test
    void deleteTest() throws Exception {
        Customer customer = service.getCustomers().getFirst();

        mockMvc.perform(delete("/api/v1/delete/customer/" + customer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(customerService).removeBeer(argumentUUIDCaptor.capture());

        assertThat(customer.getId()).isEqualTo(argumentUUIDCaptor.getValue());

    }

    @Test
    void name() throws Exception {
        Customer customer = service.getCustomers().getFirst();

        mockMvc.perform(put("/api/v1/customer/update/"+customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomer(any(UUID.class), any(Customer.class));
    }

    @Test
    void addCustomer() throws Exception {

        Customer customer = service.getCustomers().getFirst();
        customer.setId(null);
        customer.setVersion(null);

        given(customerService.addCustomer(any(Customer.class))).willReturn(service.getCustomers().get(1));

        mockMvc.perform(post("/api/v1/addcustomer")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));
    }

    @Test
    void customerToJson() throws JsonProcessingException {
        Customer customer = service.getCustomers().getFirst();
        System.out.println(objectMapper.writeValueAsString(customer));
    }

    @Test
    void getCustomers() throws Exception {
        given(customerService.getCustomers()).willReturn(service.getCustomers());

        mockMvc.perform(get("/api/v1/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(4)));
    }

    @Test
    void getCustomerById() throws Exception {

        Customer customer = service.getCustomers().getFirst();

        given(customerService.getCustomerByID(customer.getId())).willReturn(customer);

        mockMvc.perform(get("/api/v1/customer/"+ customer.getId().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.name", is(customer.getName())))
        ;
    }
}