package com.github.ankurpathak.springsession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final RedisOperationsSessionRepository sessionRepository;
    private final UserDetailsService userDetailsService;
    private final FilterConfig filterConfig;
    private final AccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final RestLogoutSuccessHandler restLogoutSuccessHandler;


    public WebSecurityConfig(RedisOperationsSessionRepository sessionRepository, UserDetailsService userDetailsService, FilterConfig filterConfig, AccessDeniedHandler restAccessDeniedHandler, RestAuthenticationEntryPoint restAuthenticationEntryPoint, RestLogoutSuccessHandler restLogoutSuccessHandler) {
        this.sessionRepository = sessionRepository;
        this.userDetailsService = userDetailsService;
        this.filterConfig = filterConfig;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.restLogoutSuccessHandler = restLogoutSuccessHandler;
    }


    @Bean
    @SuppressWarnings("deprecation")
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }




    @Autowired
    private AuthenticationManager authenticationManager;



    @Autowired
    void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
    }





    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }







    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/me").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .denyAll()
                .and()
                .logout()
               // .deleteCookies("SESSION, JSESSSIONID")
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .and()
                .addFilterAt(filterConfig.usernamePasswordAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .maximumSessions(2)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());

    }






    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry() {
        return new SpringSessionBackedSessionRegistry<>(this.sessionRepository);
    }




}
