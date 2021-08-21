package guru.springframework.msscbrewery.services.v2;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;


/**
 * Created by jt on 2019-04-20.
 */
public interface BeerServiceV2 {
	BeerDtoV2 getBeerById(UUID beerId);

	BeerDtoV2 saveBeer(BeerDtoV2 beerDtoV2);
	
	BeerDtoV2 updateBeer(UUID beerId, BeerDtoV2 beerDtoV2);
	
	Boolean deleteBeer(UUID beerId);
}
