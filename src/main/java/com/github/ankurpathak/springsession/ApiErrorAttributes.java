package com.github.ankurpathak.springsession;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@Component
public class ApiErrorAttributes extends DefaultErrorAttributes {

    private final MessageSource messageSource;

    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String message;

    public ApiErrorAttributes(MessageSource messageSource) {
        super(false);
        this.messageSource = messageSource;
        message =  MessageUtil.getMessage(messageSource, ApiMessages.UNKNOWN);

    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(request, includeStackTrace);
        map.put("status", getStatus());
        map.put("message", getMessage());
        return map;
    }

    /**
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }


    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }


}
