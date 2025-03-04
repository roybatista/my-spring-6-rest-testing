package guru.springframework.spring_6_rest_mvc.model;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Customer {

    private String name;
    private UUID id;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}
