package com.in28minutes.springboot.web.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
//	@Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin")
//                .roles("USER", "ADMIN");
//    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login").permitAll()
                //.access("hasRole('USER')")
        		.and()
                .formLogin().loginPage("/login");
    }
	
	
	//dejo este, provisorio, por q el otro no me manda al login de lucas!!!
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/login").permitAll()
//                .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
//                .formLogin();
//    }
	
}
