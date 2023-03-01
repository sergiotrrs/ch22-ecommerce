package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Address;
import org.generation.app.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {
	
	@Autowired
	IAddressRepository addressRepository;
	
	@Override
	public List<Address> getAllAddresses() {
		List<Address> allAddresses = (List<Address>) addressRepository.findAll();
		return allAddresses;
	}

	@Override
	public Address setAddress(Address address) {
		
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Address newDataAddress) {
		Address address = getAddressById(newDataAddress.getIdAddress());
		address.setAddress( newDataAddress.getAddress() );
		address.setZipCode( newDataAddress.getZipCode() );
		address.setCity( newDataAddress.getCity() );
		return addressRepository.save(address);
	}

	@Override
	public String deleteAddressById(long idAddress) {
		Address address = getAddressById( idAddress);
		addressRepository.delete(address);
		return "The address was delete with id " + idAddress;
	}

	@Override
	public Address getAddressById(long idAddress) {		
		return addressRepository.findById(idAddress)
				.orElseThrow( ()-> 
				new IllegalStateException("Address does not exist with id: " + idAddress));
	}

	@Override
	public List<Address> getAllAddressesByFkIdCustomer(long idCustomer) {
		return addressRepository.findAllByFkIdCustomerIdCustomer(idCustomer);
								
	}

}
