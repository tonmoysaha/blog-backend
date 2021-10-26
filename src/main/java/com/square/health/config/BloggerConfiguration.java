package com.square.health.config;

import com.square.health.jwt.JwtAdminOncePerRequestFilter;
import com.square.health.jwt.JwtUnAuthorizedResponseAuthenticationEntryPoint;
import com.square.health.service.impl.BloggerUserDetailService;
import com.square.health.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class BloggerConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BloggerUserDetailService bloggerUserDetailService;

    @Autowired
    private JwtAdminOncePerRequestFilter jwtBloggerOncePerRequestFilter;

    @Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.bloggerUserDetailService).passwordEncoder(PasswordUtil.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/blogger/**").hasAuthority("ROLE_BLOGGER").anyRequest().authenticated();
        http.addFilterBefore(jwtBloggerOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
