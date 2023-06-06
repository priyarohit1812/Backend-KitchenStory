package com.kitchenstory.service;

import java.util.List;

import com.kitchenstory.model.User;


public interface IUserService {
	public List<User> fetchUserList();
	public User saveUser(User user);
	public User updateUser(User user);
	public boolean deleteUser(Long userId);
	public User getUser(String emailId, String password);
	public User getUserById(Long userId);
	public boolean deleteAddresssForUser(Long userId);
	
}
