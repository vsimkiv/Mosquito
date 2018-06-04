package com.softserve.mosquito.dtos;

/**
 * DTO for user validators
 */

public class UserLoginDto {

	private Long userId;
	private String email;
	private String password;

	public UserLoginDto() {
	}

	public UserLoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public UserLoginDto(Long userId, String email, String password) {
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}


	public Long getUserId() { return userId; }

	public void setUserId(Long userId) { this.userId = userId; }
}
