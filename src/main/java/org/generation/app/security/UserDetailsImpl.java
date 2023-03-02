package org.generation.app.security;

import java.util.Collection;
import java.util.Collections;

import org.generation.app.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//paso 9: Establecer en memoria el usario, reemplaza el paso 4

public class UserDetailsImpl implements UserDetails {

	
	private Customer customer;
		
	public UserDetailsImpl(Customer customer) {		
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return Collections.emptyList(); // ToDo leer los roles de Customer
	}

	@Override
	public String getPassword() {		
		return  customer.getPassword();
	}

	@Override
	public String getUsername() {		
		return customer.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String getFullName() {
		return customer.getFirstName() + " " +customer.getLastName();
	}

}
