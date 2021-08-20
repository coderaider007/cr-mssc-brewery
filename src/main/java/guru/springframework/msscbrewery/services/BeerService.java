package guru.springframework.msscbrewery.services;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.BeerDto;


/**
 * Created by jt on 2019-04-20.
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

	BeerDto saveBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId, BeerDto beerDto);
	
	Boolean deleteBeer(UUID beerId);
}
