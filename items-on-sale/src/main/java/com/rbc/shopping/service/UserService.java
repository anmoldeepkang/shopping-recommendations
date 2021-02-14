package com.rbc.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rbc.shopping.model.User;
import com.rbc.shopping.repository.UserRepository;

/**
 * @author Anmoldeep Singh Kang
 * The service layer class for the User of the system.
 *
 */
@Service    
public class UserService {
	
	@Autowired    
	private UserRepository userRepository;    
	
	/**
	 * @return list of users
	 */
	public List<User> getAllUsers()  {    
		List<User> Users = userRepository.findAll();
		return Users;    
	}
	
	/**
	 * @param User
	 */
	public void addUser(User User)  {    
		userRepository.save(User);    
	}
	
	/**
	 * @param id
	 * @return User.
	 */
	public User getUserById(String id) {
		return userRepository.findById(id).get();
	}
	
	/**
	 * @param username
	 * @return User
	 */
	public User getUserByUsername(String username) {
		User user=userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}

		return user;
	}
	
}
