package com.rbc.shopping.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rbc.shopping.model.User;

/**
 * @author Anmoldeep Singh Kang
 * This class is wrapper for the user and implements the UserDetails interface from Spring Security.
 */
public class MyUserDetails implements UserDetails {
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param user
	 */
	public MyUserDetails(User user) {
		this.user=user;
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	private User user;
	
	/**
	 * Get Authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return password of user
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	/**
	 * @return username of user
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
