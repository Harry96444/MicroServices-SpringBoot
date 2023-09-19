package com.sp.services;

import java.util.List;

import com.sp.entities.User;

public interface UserService {
	public User saveUser(User user);
	public List<User> getAllUsers();
	public User getUserById(String userId);
	public User updateUser(User user,String userId);
	public User deleteUserById(String userId);
}
