package guru.springframework.spring_6_rest_mvc.controller;

import com.sun.net.httpserver.Headers;
import guru.springframework.spring_6_rest_mvc.model.Beer;
import guru.springframework.spring_6_rest_mvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/beer/{beerId}")
    public Beer getBeerByID(@PathVariable("beerId") UUID id){
        log.debug("Sending UUID: {}", id);

        return beerService.getBeerById(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/beers")
    //@GetMapping("/beers")
    public List<Beer> getBeers() {
        return beerService.getAllBeers();
    }

    @PostMapping("/add")
    public ResponseEntity addBeer(@RequestBody Beer beer){

        Beer newBeem = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location","/api/v1/beer/"+newBeem.getId().toString());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/update/{beerID}")
    public ResponseEntity updateBeer(@PathVariable("beerID") UUID id, @RequestBody Beer beer){

        beerService.updateBeer(id,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/beer/{beerID}")
    public ResponseEntity deletebeer(@PathVariable("beerID") UUID id) {

        beerService.removeBeer(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
