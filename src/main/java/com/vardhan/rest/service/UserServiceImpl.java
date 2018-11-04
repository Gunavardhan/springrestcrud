package com.vardhan.rest.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.vardhan.rest.bean.User;

public class UserServiceImpl implements UserService {
	
	private static List<User> usersList = new ArrayList<User>();
	static {
		usersList.add(new User(1, "Gunavardhan", "(9949026410", "Hyderabad", "TS"));
		usersList.add(new User(2, "Pawan", "(7949026412", "Guntur", "AP"));
		usersList.add(new User(3, "MSD", "(6949025414", "Ranchi", "Jharkhand"));
	}
	
	public List<User> getAllUsers() {
		return usersList;
	}
	public User getUserByUserId(Integer userId) {
		System.out.println("Checking with userid : "  +userId);
		usersList = getAllUsers();
		for (User user : usersList) {
			if(user.getUserId() == userId){
				return user;
			}
		}
		return null;
	}
	public void saveUser(User user) {
		//usersList = getAllUsers();
		usersList.add(user);
	}
	public boolean isUserExist(User user) {
		return getUserByUserId(user.getUserId()) != null;
	}
	public void updateUser(User user, Integer userId) {
		usersList = getAllUsers();
		int index = usersList.indexOf(user);
		usersList.set(index, user);
	}
	public void deleteByUserId(Integer userId) {
		for (Iterator<User> iterator = usersList.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if(user.getUserId() == userId)
				iterator.remove();
		}
		usersList = getAllUsers();
	}
	
}
