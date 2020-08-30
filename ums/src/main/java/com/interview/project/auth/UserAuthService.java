package com.interview.project.auth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.interview.project.dao.UserMapper;
import com.interview.project.model.User;

@Service("userService")
public class UserAuthService {
	@Autowired
	UserMapper userMapper;

	public String login(String username, String password) {
		User user = userMapper.login(username, password);
		String token = UUID.randomUUID().toString();
		userMapper.updateToken(token, user.getId());
		return token;
	}

	public org.springframework.security.core.userdetails.User findByToken(String token) {
		User user = userMapper.findUserByToken(token);
		org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
				user.getEmail(), user.getPassword(), true, true, true, true,
				AuthorityUtils.createAuthorityList("USER"));
		return authUser;
	}

}
