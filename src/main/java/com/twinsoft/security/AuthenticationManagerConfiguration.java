//package com.twinsoft.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
//
///**
// * @author Predrag Stanic p.stanic@levi9.com
// * @since Oct 9, 2015
// */
//@Configuration  
//public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {
//
//    @Override
//    public void init(final AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("admin")
//                .password("adminpassword")
//                .roles("ADMIN", "USER")
//            .and()
//                .withUser("user")
//                .password("user")
//                .roles("USER");
//    }
//}
