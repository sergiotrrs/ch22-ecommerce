package org.generation.app.service;

import java.util.List;

import org.generation.app.model.Address;

public interface IAddressService {
	
	public List<Address> getAllAddresses();
	
	public Address setAddress(Address address);
	
	public Address updateAddress(Address address);
	
	public String deleteAddressById(long idAddress);

	public Address getAddressById(long idAddress);
	
	public List<Address> getAllAddressesByFkIdCustomer(long idCustomer);	
	

}
