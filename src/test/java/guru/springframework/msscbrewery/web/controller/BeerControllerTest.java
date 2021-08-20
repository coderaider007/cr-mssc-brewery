package guru.springframework.msscbrewery.web.controller;



import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbrewery.services.BeerServiceImpl;
import guru.springframework.msscbrewery.web.model.BeerDto;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@MockBean
	BeerServiceImpl beerService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	
	BeerDto beerDto;
	
	@BeforeEach
	void setUp() throws Exception {
		
		beerDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Regab")
				.beerStyle("Light")
				.upc(345342524543L)
				.build();
	}

	@Test
	void testGetBeer() throws Exception {
		
		when(this.beerService.getBeerById(any(UUID.class))).thenReturn(beerDto);
		
		this.mockMvc.perform(get("/api/v1/beer/" + beerDto.getId().toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	void testSaveBeer() throws Exception {
		BeerDto validBeerDto = beerDto;
		validBeerDto.setId(null);
		
		BeerDto savedBeerDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("New Beer")
				.beerStyle("Heavy")
				.upc(34535244L)
				.build();
		String savedBeerDtoString = this.objectMapper.writeValueAsString(savedBeerDto);
		
		when(this.beerService.saveBeer(any(BeerDto.class))).thenReturn(savedBeerDto);
		
		this.mockMvc.perform(post("/api/v1/beer/").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(savedBeerDtoString))
		.andExpect(status().isCreated());
	}

	@Test
	void testHandleUpdate() throws Exception  {
		BeerDto validBeerDto = beerDto;
		
		BeerDto savedBeerDto = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("New Beer")
				.beerStyle("Heavy")
				.upc(34535244L)
				.build();
		String savedBeerDtoString = this.objectMapper.writeValueAsString(savedBeerDto);
		
		when(this.beerService.updateBeer(any(UUID.class), any(BeerDto.class))).thenReturn(savedBeerDto);
		
		this.mockMvc.perform(put("/api/v1/beer/" + validBeerDto.getId().toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(savedBeerDtoString))
		.andExpect(status().isNoContent());
	}
	
	@Test
	void testDeleteBeer() throws Exception {
		when(this.beerService.deleteBeer(any(UUID.class))).thenReturn(Boolean.TRUE);
		
		this.mockMvc.perform(delete("/api/v1/beer/" +UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}

}
