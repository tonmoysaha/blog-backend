package com.square.health.config;

import com.square.health.jwt.JwtAdminOncePerRequestFilter;
import com.square.health.jwt.JwtUnAuthorizedResponseAuthenticationEntryPoint;
import com.square.health.service.impl.AdminUserDetailService;
import com.square.health.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AdminConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminUserDetailService adminUserDetailService;

    @Autowired
    private JwtAdminOncePerRequestFilter jwtAdminOncePerRequestFilter;

    @Autowired
    private JwtUnAuthorizedResponseAuthenticationEntryPoint jwtUnAuthorizedResponseAuthenticationEntryPoint;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailService).passwordEncoder(PasswordUtil.passwordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers(HttpMethod.POST, "/admin/signIn").and()
        .ignoring().antMatchers(HttpMethod.POST, "/blogger/signIn").and()
        .ignoring().antMatchers(HttpMethod.POST, "/blogger/registration").and()
                .ignoring().antMatchers("/h2-console/**")
                .and().ignoring().antMatchers(
                "/","/assets/**","/favicon.ico", "/styles.49b426734c7f45c5b264.css",
                "/vendor.b10ca42ef9d90ad0d465.js", "/polyfills.f782e0cdb7e1242a13e4.js",
                "/main.4a9597b6dca1cacc3e28.js", "/runtime.d6c52737d4587c65265f.js");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().exceptionHandling()
                .authenticationEntryPoint(jwtUnAuthorizedResponseAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").anyRequest().fullyAuthenticated();
        http.addFilterBefore(jwtAdminOncePerRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
