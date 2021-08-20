package guru.springframework.msscbrewery.web.controller;


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

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId){
		
		if(customerId != null) {
			return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId), HttpStatus.OK);
		}
		CustomerDto customerDto = null;
		return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto){
		return new ResponseEntity<CustomerDto>(this.customerService.createCustomer(customerDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto){
		CustomerDto savedCustomerDto = this.customerService.updateCustomer(customerId, customerDto);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "/api/v1/customer" + savedCustomerDto.getId());
		return new ResponseEntity<CustomerDto>(httpHeaders, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
		this.customerService.deleteCustomer(customerId);
	}
}