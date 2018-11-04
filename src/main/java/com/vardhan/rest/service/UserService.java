package com.vardhan.rest.service;

import java.util.List;

import com.vardhan.rest.bean.User;

public interface UserService {
	List<User> getAllUsers();
	User getUserByUserId(Integer userId);
	void saveUser(User user);
	boolean isUserExist(User user);
	void updateUser(User user,Integer userId);
	void deleteByUserId(Integer userId);
	
	
}
