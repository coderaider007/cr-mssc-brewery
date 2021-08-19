package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.BeerDto;

/**
 * Created by jt on 2019-04-20.
 */
@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .build();
    }

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		if(beerDto != null) {
			beerDto.setId(UUID.randomUUID());
			return beerDto;
		}
		return null;
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		
		if(beerDto != null) {
			BeerDto updatedBeerDto = BeerDto.builder()
					.id(beerId)
					.beerName(beerDto.getBeerName())
					.beerStyle(beerDto.getBeerStyle())
					.upc(beerDto.getUpc())
					.build();
			return updatedBeerDto;
		}
		
		return null;
	}
}
