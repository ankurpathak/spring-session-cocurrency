package com.github.ankurpathak.springsession;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationEntryPoint.class);


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageSource messageSource;


    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException ex) throws IOException {
        log.info("message: {} cause: {}", ex.getMessage(), ex.getCause());
        ex.printStackTrace();
        FilterUtil.generateUnauthorized(request, response, objectMapper, messageSource);
    }



}