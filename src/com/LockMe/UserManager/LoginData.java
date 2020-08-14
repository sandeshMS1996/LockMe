package com.LockMe.UserManager;

import java.time.ZonedDateTime;

public class LoginData {
	boolean loginToken = false;
	private String email;
	private String password;
	ZonedDateTime lastLoginDateTime =  null;

	public LoginData() {
		super();
	}

	LoginData(String email, String password) {
		this.email = email;
		this.password = password;
	}
	LoginData(String email, String password, boolean loginToken, ZonedDateTime lastLogin) {
		this(email, password);
		this.lastLoginDateTime = lastLogin;
		this.loginToken = loginToken;
	}

	 public String getEmail() {
		return this.email;
	}

	 void setEmail(String email) {
		this.email = email;
	}


	 String getPassword() {
		return this.password;
	}
	 void setPassword(String pass) {
			this.password =  pass;
		}
	 
	 public boolean getLoginToken() {
		 return this.loginToken;
	 }
	 
	 public ZonedDateTime getlastLoginDateTime() {
		 return this.lastLoginDateTime;
	 }
	

	

	@Override
	public String toString() {
		return "LoginData [loginToken=" + loginToken + ", email=" + email + ", lastLoginDateTime=" + lastLoginDateTime
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof LoginData) {
			if(this.email.equals(((LoginData) obj).getEmail()))
				return true;
		}
		return false;	
	}
}

