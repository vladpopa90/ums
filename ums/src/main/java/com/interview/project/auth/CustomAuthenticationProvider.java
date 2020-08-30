package com.interview.project.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
//implements AuthenticationProvider {

//    @Autowired
//    private UserMapper userService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        User user = userService.findUserByEmail(email);
//
//        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }

	@Autowired
	UserAuthService userService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String userName,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

		Object token = usernamePasswordAuthenticationToken.getCredentials();
		org.springframework.security.core.userdetails.User user = userService.findByToken(String.valueOf(token));
		if (user == null)
			throw (new UsernameNotFoundException("Cannot find user with authentication token=" + token));
		return user;
	}
}