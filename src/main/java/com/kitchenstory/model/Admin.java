package com.kitchenstory.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ks_user")
@DiscriminatorValue("Admin")
public class Admin extends User {

	public Admin() {
		super();
	}
	
	public Admin(Long adminId,  String email, String firstName, String lastName, String mobile, String password) {
		super(adminId, email, firstName, lastName, mobile, password);
	}

}
