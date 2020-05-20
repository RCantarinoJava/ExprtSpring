package com.cantarino.brewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cantarino.brewer.services.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	private static final String layout = "/layout/**";
	private static final String imagens = "/images/**";
	private static final String loginPage = "/login";
	private static final String logoutPage = "/logout";
	private static final String acessoNegado = "/acessonegado";
	private static final int sessionConcurrent = 1;
	
	
	@Autowired
	private UserDetailsService userService;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService)
			.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {

		web.ignoring()
		   .antMatchers(layout)
		   .antMatchers(imagens);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/cidades/nova").hasRole("CADASTRAR_CIDADE")
			.antMatchers("/usuarios/**").hasRole("CADASTRAR_USUARIO")
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
				.loginPage(loginPage)
				.permitAll()
			.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
			.and()
			.exceptionHandling().accessDeniedPage(acessoNegado)
			.and()
			.sessionManagement()
			.maximumSessions(sessionConcurrent); //sessões por usuario ao mesmo tempo
			
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
