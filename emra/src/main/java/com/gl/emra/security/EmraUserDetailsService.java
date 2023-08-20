package com.gl.emra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.emra.dao.UserDao;
import com.gl.emra.model.UserEntity;

@Service
public class EmraUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userDao.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + "User not Found!!"));

		EmraUserDetails emraUserDetails = new EmraUserDetails(user);
		return emraUserDetails;
	}

}
