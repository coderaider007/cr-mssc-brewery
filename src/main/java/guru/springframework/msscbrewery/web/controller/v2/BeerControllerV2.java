package guru.springframework.msscbrewery.web.controller.v2;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;


/**
 * Created by jt on 2019-04-20.
 */
@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){

        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<BeerDtoV2> saveBeer(@RequestBody BeerDtoV2 beerDto){
    	BeerDtoV2 savedBeerDto = beerServiceV2.saveBeer(beerDto);
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Location", "/api/v1/beer/" + savedBeerDto.getId().toString());
    	return new ResponseEntity<BeerDtoV2>(savedBeerDto, headers, HttpStatus.CREATED);
    }
    
    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto){
    	BeerDtoV2 updateBeerDto = beerServiceV2.updateBeer(beerId, beerDto);
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Location", "/api/v1/beer/" + updateBeerDto.getId().toString());
    	return new ResponseEntity<BeerDtoV2>(headers, HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
    	
    	this.beerServiceV2.deleteBeer(beerId);
    }

}
