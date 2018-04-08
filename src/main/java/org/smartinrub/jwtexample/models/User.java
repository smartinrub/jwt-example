package org.smartinrub.jwtexample.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
	
	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
