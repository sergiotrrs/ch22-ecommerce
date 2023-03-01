package org.generation.app.controller;

import java.util.List;

import org.generation.app.model.*;
import org.generation.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/addresses")
public class AddressController {

	@Autowired
	IAddressService addressService;
	
	@GetMapping //localhost:8080/api/addresses
	public List<Address> getAllAddresses(){
		return addressService.getAllAddresses();		
	}
	
	
	@GetMapping("{id}") //localhost:8080/api/addresses/2
	public ResponseEntity<?> getAddressById(@PathVariable("id") long idAddress) {
		try {
			return new ResponseEntity<Address>(
					addressService.getAddressById(idAddress), 
					HttpStatus.OK);													
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND );
		}
	}
	
	@PostMapping //localhost:8080/api/addresses
	public ResponseEntity<?> setNewAddress(@RequestBody Address address) {
		try {
			return new ResponseEntity<Address>(
					addressService.setAddress(address), 
					HttpStatus.CREATED);					
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST );
		}
		
	}
	
	@PutMapping
	public ResponseEntity<?> updateAddress(@RequestBody Address address ){
		try {
			return new ResponseEntity<Address>(
					addressService.updateAddress(address), 
					HttpStatus.CREATED);					
			
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.BAD_REQUEST );
		}
	}

	
	@DeleteMapping("{id}") //localhost:8080/api/addresses/2
	public ResponseEntity<?> deleteAddressById(@PathVariable("id") long idAddress) {
		try {
			return new ResponseEntity<String>(
					addressService.deleteAddressById(idAddress), 
					HttpStatus.OK);													
		} catch (IllegalStateException e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND );
		}
	}	
	
	@GetMapping("/customer/{id}") //localhost:8080/api/addresses/customer/2
	public List<Address> getAllAddressesByFkIdCustomer(@PathVariable("id") long idCustomer){
		return addressService.getAllAddressesByFkIdCustomer(idCustomer);
	}
	
}
