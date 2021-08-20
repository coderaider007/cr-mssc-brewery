package guru.springframework.msscbrewery.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		if(customerDto != null) {
			customerDto.setId(UUID.randomUUID());
			return customerDto;
		}
		return null;
	}

	@Override
	public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
		if(customerId != null && customerDto != null) {
			return customerDto;
		}
		return null;
	}

	@Override
	public Boolean deleteCustomer(UUID customerId) {
		log.info("Customer with Id : " + customerId + " has been deleted");
		return Boolean.FALSE;
	}

}
