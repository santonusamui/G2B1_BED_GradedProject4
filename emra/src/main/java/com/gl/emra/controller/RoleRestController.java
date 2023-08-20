package com.gl.emra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.emra.dao.RoleDao;
import com.gl.emra.model.Role;

@RestController
@RequestMapping("/roles")
public class RoleRestController {

	private RoleDao roleDao;

	public RoleRestController(RoleDao roleDao) {

		this.roleDao = roleDao;
	}

	@PostMapping
	public ResponseEntity<String> createRole(@RequestBody Role role) {

		if (roleDao.existByName(role.getName()) != null) {

			return new ResponseEntity<>(role.getName() + " Role Already Exists!!", HttpStatus.BAD_REQUEST);
		} else {
			roleDao.save(role);
			return new ResponseEntity<>(role.getName() + " Created!!", HttpStatus.OK);
		}
	}

}
