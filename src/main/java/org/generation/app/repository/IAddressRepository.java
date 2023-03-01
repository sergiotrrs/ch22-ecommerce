package org.generation.app.repository;

import java.util.List;

import org.generation.app.model.*;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepository extends CrudRepository<Address, Long>  {
	
		List<Address> findAllByFkIdCustomer(long idCustomer);

}
