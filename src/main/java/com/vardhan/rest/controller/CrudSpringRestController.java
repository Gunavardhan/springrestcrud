package com.vardhan.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vardhan.rest.bean.ApiResponse;
import com.vardhan.rest.bean.User;
import com.vardhan.rest.service.UserService;

@RestController
@RequestMapping("/api")
public class CrudSpringRestController {
	
	@Autowired
	private UserService userService = null;
	
	@RequestMapping(value="/samplemsg", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<ApiResponse> welcome(){
		ApiResponse sm = new ApiResponse();
		sm.setUsername("Guna");
		if(sm.getUsername().equals("Guna")){
			return new ResponseEntity<ApiResponse>(sm, HttpStatus.OK);
		}
		sm = null;
		return new ResponseEntity<ApiResponse>(sm, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<ApiResponse> getAllUsers(){
		List<User> getAllUsers = userService.getAllUsers();
		ApiResponse sm = new ApiResponse();
		sm.setUsers(getAllUsers);
		if(getAllUsers.size() != 0){
			return  new ResponseEntity<ApiResponse>(sm ,HttpStatus.OK);
		}
		sm = null;
		return new ResponseEntity<ApiResponse>(sm,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/getUserById/{userId}", method=RequestMethod.GET, headers="Accept=application/json")
	public ResponseEntity<ApiResponse> getUserByUserId(@PathVariable Integer userId){
		System.out.println("User By Get UserId");
		User getuserByUserId = userService.getUserByUserId(userId);
		ApiResponse sm = new ApiResponse();
		sm.setUser(getuserByUserId);
		if(null != getuserByUserId){
			return new ResponseEntity<ApiResponse>(sm,HttpStatus.OK);
		}
		sm = null;
		return new ResponseEntity<ApiResponse>(sm,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST, headers="Accept=application/json")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody User user){
		System.out.println("Save new User");
		if(userService.isUserExist(user)){
			System.out.println(user.getUserId() + " : Already exist.");
			return new ResponseEntity<ApiResponse>(HttpStatus.CONFLICT);
		}
		ApiResponse ar = new ApiResponse();
		ar.setMessage("user created");
		if(null != user){
			userService.saveUser(user);
			return new ResponseEntity<ApiResponse>(ar, HttpStatus.OK);
		}
		ar = null;
		return new ResponseEntity<ApiResponse>(ar, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/updateUser/{userId}", method=RequestMethod.PUT, headers="Accept=application/json")
	public ResponseEntity<ApiResponse> updateUser(@RequestBody User user,@PathVariable("userId") Integer userId){
		System.out.println("Updating User : " + userId);
		User currentUser = userService.getUserByUserId(userId);
		if(currentUser == null){
			System.out.println("User with id " + userId + " not found");
            return new ResponseEntity<ApiResponse>(HttpStatus.NOT_FOUND);
		}
			currentUser.setUsername(user.getUsername());;
			currentUser.setMobile(user.getMobile());
			currentUser.setCity(user.getCity());
			currentUser.setState(user.getState());
			ApiResponse ar = new ApiResponse();
			ar.setMessage("userupdated");
			userService.updateUser(currentUser, userId);
		return new ResponseEntity<ApiResponse>(ar, HttpStatus.OK);
		}
	
	@RequestMapping(value="/deleteUserById/{userId}", method=RequestMethod.DELETE, headers="Accept=application/json")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable("userId") Integer userId){
		System.out.println("Deleter user by  : " + userId);
		User currentUser = userService.getUserByUserId(userId);
		if(currentUser == null){
			System.out.println("User Id Not FOund : " + userId);
			return new ResponseEntity<ApiResponse>(HttpStatus.NOT_FOUND);
		}
		ApiResponse ar = new ApiResponse();
		ar.setMessage("User Deleted");
		userService.deleteByUserId(userId);
		return new ResponseEntity<ApiResponse>(ar, HttpStatus.OK);
	}
}
