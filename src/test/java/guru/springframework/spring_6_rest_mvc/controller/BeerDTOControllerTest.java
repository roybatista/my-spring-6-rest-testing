package guru.springframework.spring_6_rest_mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import guru.springframework.spring_6_rest_mvc.service.BeerService;
import guru.springframework.spring_6_rest_mvc.service.BeerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerDTOControllerTest {

    @Autowired
    MockMvc mockitoBean;

    @MockitoBean
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> argumentUUIDCaptor;

    @Captor
    ArgumentCaptor<BeerDTO> beerArgumentCaptor;

    BeerServiceImpl beerServiceImpl;

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }

    @Test
    void addBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.getAllBeers().getFirst();
        beer.setId(null);
        beer.setVersion(null);

        given(beerService.createBeer(any(BeerDTO.class))).willReturn(beerServiceImpl.getAllBeers().get(1));

        mockitoBean.perform(post("/api/v1/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("location"));

    }

    @Test
    void deleteBeerTest() throws Exception {
        BeerDTO beer = beerServiceImpl.getAllBeers().getFirst();

        mockitoBean.perform(delete("/api/v1/delete/beer/"+beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(beerService).removeBeer(argumentUUIDCaptor.capture());

        assertThat(beer.getId()).isEqualTo(argumentUUIDCaptor.getValue());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDTO beer = beerServiceImpl.getAllBeers().getFirst();

        mockitoBean.perform(put("/api/v1/update/"+beer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beer)))
                .andExpect(status().isNoContent());

        verify(beerService).updateBeer(any(UUID.class), any(BeerDTO.class));
    }

    @Test
    void createNewBeer() throws JsonProcessingException {
        BeerDTO beer = beerServiceImpl.getAllBeers().getFirst();
        System.out.println(objectMapper.writeValueAsString(beer));
    }

    @Test
    void getBeers() throws Exception {

        given(beerService.getAllBeers()).willReturn(beerServiceImpl.getAllBeers());

        mockitoBean.perform(get("/api/v1/beers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));

    }

    @Test
    void getBeetByIDNotFound() throws Exception {

        given(beerService.getBeerById(any(UUID.class))).willThrow(NotFoundException.class);

        mockitoBean.perform((get("/api/v1/beer/"+UUID.randomUUID()))).andExpect(status().isNotFound());
    }

    @Test
    void getBeerByID() throws Exception{

        BeerDTO testBeer = beerServiceImpl.getAllBeers().get(0);

        given(beerService.getBeerById(testBeer.getId())).willReturn(Optional.of(testBeer));

        mockitoBean.perform(get("/api/v1/beer/" + testBeer.getId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is( testBeer.getId().toString() )))
                .andExpect(jsonPath("$.beerName", is(testBeer.getBeerName() )));

    }
}