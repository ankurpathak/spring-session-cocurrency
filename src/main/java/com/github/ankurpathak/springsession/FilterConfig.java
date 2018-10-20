package com.github.ankurpathak.springsession;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.UUID;

@Configuration
public class FilterConfig {

    @Autowired
    private final RestSimpleUrlAuthenticationFailureHandler restAuthenticationFailureHandler;
    @Autowired
    private  final RestSavedRequestAwareAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    private final ObjectMapper objectMapper;





    public static final String ANNONYMOUS_KEY = UUID.randomUUID().toString();

    public FilterConfig(RestSimpleUrlAuthenticationFailureHandler restAuthenticationFailureHandler, RestSavedRequestAwareAuthenticationSuccessHandler restAuthenticationSuccessHandler, ObjectMapper objectMapper) {
        this.restAuthenticationFailureHandler = restAuthenticationFailureHandler;
        this.restAuthenticationSuccessHandler = restAuthenticationSuccessHandler;
        this.objectMapper = objectMapper;
    }


    protected RestUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        RestUsernamePasswordAuthenticationFilter filter = new RestUsernamePasswordAuthenticationFilter(objectMapper);
        filter.setAuthenticationManager(authenticationManager);
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);
        return filter;
    }




}
