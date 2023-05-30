package com.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.helper.UserFoudException;
import com.exam.repo.RoleRepo;
import com.exam.repo.UserRoleRepo;
import com.exam.service.UserService;

public @RestController @RequestMapping("/user") @CrossOrigin("*") class UserController {
	@Autowired
	UserService userService;

	@Autowired
	RoleRepo roleService;

	@Autowired
	UserRoleRepo userRoleService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/")
	public User createUser(Model model, @RequestBody User user) throws Exception {
		User u = userService.findByUsername(user.getUsername());
		if (u != null) {
			System.out.println("User is already there !!!");
			throw new UserFoudException();
		}else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			Role role = roleService.findById((long) 2).get();
			UserRole authority = new UserRole();
			authority.setRole(role);
			authority.setUser(user);
			userService.save(user);
			userRoleService.save(authority);
			return userService.save(user);
		}
	}

	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return userService.findByUsername(username);
	}

	@DeleteMapping("/{username}")
	public void deletaUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
	}

}
