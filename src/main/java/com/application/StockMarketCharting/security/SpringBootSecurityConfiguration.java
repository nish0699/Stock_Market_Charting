package com.application.StockMarketCharting.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringBootSecurityConfiguration extends WebSecurityConfigurerAdapter {
//	@Value("${security.enable-csrf}")
//   	private boolean csrfEnabled;

	
	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http 
//			.authorizeRequests()
//			.antMatchers("/**");
//			.and()
//			.formLogin().loginPage("/login").permitAll();
		http
			.authorizeRequests()
			.antMatchers("/**")
			.permitAll().anyRequest().anonymous();

//		http
//			.authorizeRequests()
//			.antMatchers("/")
//			.permitAll()
//			.anyRequest().authenticated();
//
//		http.formLogin().loginPage("/")
//	    	.permitAll().and().logout().permitAll();

		//if(csrfEnabled){
			http.csrf().disable();
//		}
	}
}
