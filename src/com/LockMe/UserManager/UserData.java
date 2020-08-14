package com.LockMe.UserManager;

public class UserData {
	/**
	 * @author Sandesh MS
	 */
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	UserData(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	String getFirstName() {
		return this.firstName;
	}
	
	String getLastName() {
		return this.lastName;
	}
	
	String getEmail() {
		return this.email;
	}
	
	
    String getPassword() {
		return this.password;
	}
	
	@Override
	public String toString() {
		return "UserDetails [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UserData && this.email.equals(((UserData) obj).email))
				return true;
		return  false;
	}
	
}

