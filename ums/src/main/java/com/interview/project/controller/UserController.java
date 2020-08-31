package com.interview.project.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.project.auth.UserAuthService;
import com.interview.project.dao.UserMapper;
import com.interview.project.model.User;

@RestController
public class UserController {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	UserAuthService userService;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "api/users")
	public List<User> getUsers() {
		return userMapper.findAllUsers();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping("/login")
	public String login(@RequestBody User user) {
		String token = userService.login(user.getEmail(), user.getPassword());
		if (StringUtils.isEmpty(token)) {
			return "";
		}
		return token;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/user", produces = "application/json;", method = RequestMethod.GET)
	@ResponseBody
	public User currentUserName(@RequestHeader(name = "Authorization") String token) {
		User currentUser = null;
		System.out.println(token);
		if (token != null && !token.isBlank()) {
			currentUser = userMapper.findUserByToken(token.split(" ")[1]);
		}
		return currentUser;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/deleteUser/{id}", method = RequestMethod.GET)
	public boolean deleteUser(@PathVariable("id") int id) {
		try {
			userMapper.deleteUser(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/addUser")
	public boolean addUser(@RequestBody User user) {
		try {
			userMapper.insertUser(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/changeUserGroup")
	public boolean chnageUserGroup(@RequestBody User user) {
		try {
			userMapper.updateGroup(user.getId(), user.getGroupId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
