package com.github.ankurpathak.springsession;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(users.get(username)).orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found.", username)));
    }




    private static  final Map<String, com.github.ankurpathak.springsession.User> users = new LinkedHashMap<>();


    public static String USERNAME_1 = "ankurpathak@live.in";
    public static String USERNAME_2 = "amarmule@live.in";

    static {
        users.put(USERNAME_1, new User(USERNAME_1, "password", "ankurpathak" , Collections.singleton("ROLE_ADMIN")));
        users.put(USERNAME_2, new User(USERNAME_2, "password", "amarmule" , Collections.singleton("ROLE_ADMIN")));
    }
}
