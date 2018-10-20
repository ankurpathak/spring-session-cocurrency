package com.github.ankurpathak.springsession;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RestUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private boolean postOnly = true;
    private final ObjectMapper objectMapper;

    public RestUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected String obtainPassword(HttpServletRequest request)  {
     //   if(WebUtil.isAjax(request)){
            return obtainLoginRequest(request).getPassword();
     //   }else {
       //     return super.obtainPassword(request);
     //   }
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
       // if(WebUtil.isAjax(request))
            return obtainLoginRequest(request).getUsername();
       // else
       //     return super.obtainUsername(request);
    }


    private LoginRequestDto obtainLoginRequest(HttpServletRequest request) {
        LoginRequestDto loginRequest = (LoginRequestDto) request.getAttribute(LoginRequestDto.class.getName());
        if(loginRequest == null){
            try{
                loginRequest = this.objectMapper.readValue(request.getReader(), LoginRequestDto.class);
                request.setAttribute(LoginRequestDto.class.getName(), loginRequest);
            }catch (IOException ex){
                loginRequest = new LoginRequestDto("", "");
            }
        }
        return loginRequest;
    }


}
