package com.rbc.shopping.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rbc.shopping.model.User;
import com.rbc.shopping.repository.UserRepository;

/**
 * @author Anmoldeep Singh Kang
 * This class implements the service required to retrieve the user during the authentication.
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserRepository userRepository;
	
	/**
	 * @return user's details.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
        return new MyUserDetails(user);
	}

}
