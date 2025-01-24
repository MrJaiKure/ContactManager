package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.smart.services.impl.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig {


//	 @Bean
//     public UserDetailsService userDetailsService() {
//
//     UserDetails user1 = User
//     .withDefaultPasswordEncoder()
//     .username("admin123")
//     .password("admin123")
//     .roles("ADMIN", "USER")
//     .build();
//
//     UserDetails user2 = User
//     .withDefaultPasswordEncoder()
//     .username("user123")
//    .password("password")
//     // .roles(null)
//     .build();
//
//     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
//     user2);
//     return inMemoryUserDetailsManager;
//
//     }
	
	
	@Autowired
	private SecurityCustomUserDetailsService userDetailsService;

	
	// configuration of authentication provider 
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider =new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
		
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(authorize->{
			authorize.requestMatchers("/login").permitAll();
		authorize.requestMatchers("/user/**").authenticated();
		authorize.anyRequest().permitAll();
		
		});
		//form default login
		httpSecurity.formLogin(formlogin->{
			formlogin.loginPage("/login");
			formlogin.loginProcessingUrl("/authenticate");
			// formlogin.successForwardUrl("/user/profile");
			formlogin.defaultSuccessUrl("/user/profile", true);
			formlogin.failureForwardUrl("/login?error=true");
			formlogin.usernameParameter("email");
     		formlogin.passwordParameter("password");
		});
		httpSecurity.oauth2Login(oauth->{
			oauth.loginPage("/login");
		});

		httpSecurity.csrf().disable(); 
		
		httpSecurity.logout(formlogout->{
			formlogout.logoutUrl("/logout")
			.logoutSuccessUrl("/login") // Redirect to /login after successful logout
			.permitAll(); // Allow all users to access the logout URL
		});
	
		return httpSecurity.build();

		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
