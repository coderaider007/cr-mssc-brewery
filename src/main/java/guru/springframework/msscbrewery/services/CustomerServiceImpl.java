package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		if(customerId != null) {
			return CustomerDto.builder()
					.id(customerId)
					.name("James")
					.build();
		}
		return null;
	}

}
