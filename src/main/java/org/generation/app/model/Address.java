package org.generation.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data //Genera los setters and getters con Lombok
@Table( name= "address")
public class Address {

	public static final int FIELD_MAX_LENGTH = 160;
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column(name="id_address")
	private long idAddress;
	@Column(name="address",nullable = false ,length= FIELD_MAX_LENGTH)
	private String address;
	@Column(name="zip_code", length= FIELD_MAX_LENGTH)
	private String zipCode;
	@Column(name="city", length= FIELD_MAX_LENGTH)
	private String city;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="fk_id_customer")
	private Customer fkIdCustomer;
	
}
