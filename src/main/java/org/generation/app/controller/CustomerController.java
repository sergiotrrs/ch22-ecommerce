package org.generation.app.controller;

import java.util.List;

import org.generation.app.dto.CustomerDto;
import org.generation.app.model.Customer;
import org.generation.app.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
@CrossOrigin(origins="*")
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')") //paso 8: autorizar la solicitud solo para Admin
	@GetMapping //localhost:8080/api/customers
	public List<CustomerDto> getAllCustomers(){
		return customerService.getAllCustomersDto();		
	}
	
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')") //paso 8
	@GetMapping("active") //localhost:8080/api/customers/active
	public List<Customer> getAllActiveCustomers(){
		return customerService.getAllActiveCustomers();
	}
	
	@GetMapping("{id}") //localhost:8080/api/customers/2
	public ResponseEntity<?> getCustomerById(@PathVariable("id") long idCustomer) {
		try {
			return new ResponseEntity<CustomerDto>(
					customerService.getCustomerDtoById(idCustomer), 
					HttpStatus.OK);													
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND );
		}
	}
	
	@PostMapping //localhost:8080/api/customers
	public ResponseEntity<?> setNewCustomer(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<Customer>(
					customerService.setCustomer(customer), 
					HttpStatus.CREATED);					
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST );
		}
		
	}
	
	@PutMapping
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer ){
		try {
			return new ResponseEntity<Customer>(
					customerService.updateCustomer(customer), 
					HttpStatus.CREATED);					
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST );
		}
	}

	
	@DeleteMapping("{id}") //localhost:8080/api/customers/2
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") long idCustomer) {
		try {
			return new ResponseEntity<String>(
					customerService.deleteCustomerById(idCustomer), 
					HttpStatus.OK);													
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND );
		}
	}	
	
	
}
