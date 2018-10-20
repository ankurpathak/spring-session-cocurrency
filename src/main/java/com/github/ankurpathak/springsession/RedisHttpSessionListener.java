package com.github.ankurpathak.springsession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;

@Component
public class RedisHttpSessionListener implements HttpSessionListener {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
       // savePrincipalNameIndexName(event.getSession());
        RedisOperationsSessionRepository sessionRepository = applicationContext.getBean(RedisOperationsSessionRepository.class);
        Map<String, ?> sessions = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, CustomUserDetailsService.USERNAME_1);
        System.out.printf("Number of sessions for username %s: %d%n",CustomUserDetailsService.USERNAME_1, sessions.size());
        System.out.printf("Session Created: %s%n",event.getSession().getId());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        RedisOperationsSessionRepository sessionRepository = applicationContext.getBean(RedisOperationsSessionRepository.class);
        Map<String, ?> sessions = sessionRepository.findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, CustomUserDetailsService.USERNAME_1);
        System.out.printf("Number of sessions for username %s: %d%n",CustomUserDetailsService.USERNAME_1, sessions.size());
        System.out.printf("Session Destroyed: %s%n",event.getSession().getId());
    }


    
}
