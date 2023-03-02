package org.generation.app.dto;

import lombok.Data;

@Data
public class CustomerDto {
	
	private long idCustomer;
	private String firstName;
	private String lastName;
	private String email;
	private String avatar;

}
