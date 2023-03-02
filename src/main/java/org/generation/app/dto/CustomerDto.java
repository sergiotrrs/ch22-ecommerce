package org.generation.app.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CustomerDto {
	
	private long idCustomer;
	private String firstName;
	private String lastName;
	private String email;
	private String avatar;

}
