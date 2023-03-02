package org.generation.app.repository;

import java.util.List;

import org.generation.app.model.*;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Long>  {
	
	// Buscar todas las direcciones por el id del cliente.
		List<Address> findAllByFkIdCustomerIdCustomer(long idCustomer);
	// Buscar todas las direcciones por el email del cliente
		List<Address> findAllByFkIdCustomerEmail(String email);
	// Buscar todas las direcciones con el misco código postal	
		List<Address> findAllByZipCode(String ZipCode);

}
