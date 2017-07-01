/**
 *
 */
package com.twinsoft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * @author Zolt Egete z.egete@levi9.com
 * @since Aug 17, 2015
 */
@Configuration
@EnableWebSecurity
public class AppWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final String X_XSRF_TOKEN = "X-XSRF-TOKEN";
	
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("adminpassword").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("userpassword").roles("USER");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	 http.authorizeRequests().antMatchers("**/oauth/token").permitAll()
         .and().authorizeRequests().anyRequest().authenticated()
         .and().httpBasic()
         .and().csrf().disable()
         .logout().permitAll();
    }
        
}
    
