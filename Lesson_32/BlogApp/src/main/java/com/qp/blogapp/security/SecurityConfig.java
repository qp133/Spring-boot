package com.qp.blogapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomFilter customFilter;

    @Autowired
    private AuthenticationEntryPointCustom authenticationEntryPointCustom;

    @Autowired
    private AccessDeniedHandlerCustom accessDeniedHandlerCustom;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/auth/**", "/api/v1/**").permitAll() // Cho phép ai cũng có thể đi qua
                    .antMatchers("/api/admin/**").permitAll() // Cần quyền admin mới được đi qua
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPointCustom) // Xử lý authentication
                    .accessDeniedHandler(accessDeniedHandlerCustom) // Xử lý forbidden
                .and()
                .logout()
                    .logoutUrl("/handle-logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
