package com.vardhan.rest.bean;

public class User {

	private Integer userId1;
	private String username;
	private String mobile;
	private String city;
	private String state;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User(){}
	public User(Integer userId, String username, String mobile, String city, String state) {
		super();
		this.userId = userId;
		this.username = username;
		this.mobile = mobile;
		this.city = city;
		this.state = state;
	}
	
}
