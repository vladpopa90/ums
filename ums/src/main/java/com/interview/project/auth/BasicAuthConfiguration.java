package com.interview.project.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicAuthConfiguration extends WebSecurityConfigurerAdapter {

	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(new AntPathRequestMatcher("/api/**"));

	CustomAuthenticationProvider provider;

	public BasicAuthConfiguration(final CustomAuthenticationProvider authenticationProvider) {
		super();
		this.provider = authenticationProvider;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(provider);
	}

	@Override
	public void configure(final WebSecurity webSecurity) {
		webSecurity.ignoring().antMatchers("/login");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.and().authenticationProvider(provider)
				.addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class).authorizeRequests()
				.requestMatchers(PROTECTED_URLS).authenticated()
				.and().csrf().disable().formLogin().disable().httpBasic().disable().logout().disable();
	}

	@Bean
	AuthFilter authenticationFilter() throws Exception {
		final AuthFilter filter = new AuthFilter(PROTECTED_URLS);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

	@Bean
	AuthenticationEntryPoint forbiddenEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
	}

}