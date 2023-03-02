package org.generation.app.security;

import org.generation.app.model.Customer;
import org.generation.app.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// paso 10: Leer los datos del usuario desde la DB 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	ICustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//Leer de la base de datos para encontrar el cliente
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow( ()-> new UsernameNotFoundException("User not found with email: " + email));
		
		return new UserDetailsImpl(customer);
	}

}
