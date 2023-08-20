package com.gl.emra.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.emra.dao.RoleDao;
import com.gl.emra.dao.UserDao;
import com.gl.emra.model.Role;
import com.gl.emra.model.UserEntity;

@RestController
@RequestMapping("/users")
public class UserRestController {

	private UserDao userDao;

	private PasswordEncoder passEncoder;

	public UserRestController(UserDao userDao, RoleDao roleDao, PasswordEncoder passEncoder) {

		this.userDao = userDao;

		this.passEncoder = passEncoder;
	}

	@PostMapping
	public ResponseEntity<String> register(@RequestBody UserEntity userCame) {

		if (userDao.existByUsername(userCame.getUsername()) != null
				&& userDao.existByUsername(userCame.getUsername()).equalsIgnoreCase(userCame.getUsername())) {

			return new ResponseEntity<>(userCame.getUsername() + " User Already Exists!!", HttpStatus.BAD_REQUEST);
		}

		UserEntity user = new UserEntity();
		user.setUsername(userCame.getUsername());
		user.setPassword(passEncoder.encode(userCame.getPassword()));

		List<Role> roles = userCame.getRoles();
		user.setRoles(roles);

		// Role roles = roleDao.findByName("USER").get();
		// user.setRoles(Collections.singletonList(roles));

		userDao.save(user);

		return new ResponseEntity<>(user.getUsername() + " Registered Successfully!!", HttpStatus.OK);
	}

}
