package guru.springframework.msscbrewery.services.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import guru.springframework.msscbrewery.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jt on 2019-04-20.
 */
@Service
@Slf4j
public class BeerServiceImplV2 implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return BeerDtoV2.builder().id(UUID.randomUUID())
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.ALE)
                .build();
    }

	@Override
	public BeerDtoV2 saveBeer(BeerDtoV2 beerDtoV2) {
		if(beerDtoV2 != null) {
			beerDtoV2.setId(UUID.randomUUID());
			return beerDtoV2;
		}
		return null;
	}

	@Override
	public BeerDtoV2 updateBeer(UUID beerId, BeerDtoV2 beerDtoV2) {
		
		if(beerDtoV2 != null) {
			BeerDtoV2 updatedBeerDto = BeerDtoV2.builder()
					.id(beerId)
					.beerName(beerDtoV2.getBeerName())
					.beerStyle(beerDtoV2.getBeerStyle())
					.upc(beerDtoV2.getUpc())
					.build();
			return updatedBeerDto;
		}
		
		return null;
	}
	
	@Override
	public Boolean deleteBeer(UUID beerId) {
		log.info("Beer with id : " + beerId + " has been deleted");
		return Boolean.TRUE;
	}
}
