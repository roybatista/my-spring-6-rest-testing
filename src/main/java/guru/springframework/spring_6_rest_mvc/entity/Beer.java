package guru.springframework.spring_6_rest_mvc.entity;

import guru.springframework.spring_6_rest_mvc.model.BeerStyle;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Beer {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(columnDefinition = "varchar", length = 36, nullable = false, updatable = false)
    private UUID id;
    @Version
    private Integer version;
    private String beerName;
    private BeerStyle bearStyle;
    private String upc;
    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
