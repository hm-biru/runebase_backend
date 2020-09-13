package com.runebase;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class RunebaseConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// unbeschränkter Zugriff auf Ressourcen und Login/Logout
				.antMatchers("/assets/**", "/", "/login**", "/logout**").permitAll()
				// Administratorzugriff auf Datenbank
				.antMatchers("/h2-console/**").hasRole("Admin")
				// Authentifizierung Voraussetzung für alles andere
				.anyRequest().fullyAuthenticated()
				// Login-Handling
				.and().formLogin().and().logout().logoutSuccessUrl("/").permitAll().and().formLogin()
				.loginPage("/login");
	}
}
