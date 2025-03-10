package guru.springframework.spring_6_rest_mvc.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseCustomer {

    private String status;
    private String discription;
    private CustomerDTO customer;

}
