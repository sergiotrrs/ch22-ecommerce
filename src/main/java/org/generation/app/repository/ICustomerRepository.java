package org.generation.app.repository;

import java.util.List;

import org.generation.app.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long>  {
	// Regresar todos los clientes activos
	List<Customer> findAllByActive(boolean stateOfActive);
	// Busca la existencia del cliente
	boolean existsByEmail(String email);
}
