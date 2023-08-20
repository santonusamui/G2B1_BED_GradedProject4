package com.gl.emra.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gl.emra.model.Role;
import com.gl.emra.model.UserEntity;

public class EmraUserDetails implements UserDetails {

	private UserEntity userEntityObj;

	public EmraUserDetails(UserEntity userEntityObj) {

		this.userEntityObj = userEntityObj;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> results = new ArrayList<>();

		for (Role roleObj : userEntityObj.getRoles()) {

			String roleName = roleObj.getName();
			SimpleGrantedAuthority emra = new SimpleGrantedAuthority(roleName);
			results.add(emra);

		}
		return results;
	}

	@Override
	public String getPassword() {

		return userEntityObj.getPassword();
	}

	@Override
	public String getUsername() {

		return userEntityObj.getUsername();
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
