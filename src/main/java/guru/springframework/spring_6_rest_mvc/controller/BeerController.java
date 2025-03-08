package guru.springframework.spring_6_rest_mvc.controller;

import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import guru.springframework.spring_6_rest_mvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/beer/{beerId}")
    public BeerDTO getBeerByID(@PathVariable("beerId") UUID id){

        log.info("Sending UUID");
        //log.info("Sending UUID: {}", id);

        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/beers")
    //@GetMapping("/beers")
    public List<BeerDTO> getBeers() {
        return beerService.getAllBeers();
    }

    @PostMapping("/add")
    public ResponseEntity addBeer(@RequestBody BeerDTO beer){

        BeerDTO newBeem = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("location","/api/v1/beer/"+newBeem.getId());

        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

    @PutMapping("/update/{beerID}")
    public ResponseEntity updateBeer(@PathVariable("beerID") UUID id, @RequestBody BeerDTO beer){

        beerService.updateBeer(id,beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/beer/{beerID}")
    public ResponseEntity deletebeer(@PathVariable("beerID") UUID id) {

        beerService.removeBeer(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException(){
//        System.out.println("This is my Exception");
//        return ResponseEntity.notFound().build();
//    }

}
