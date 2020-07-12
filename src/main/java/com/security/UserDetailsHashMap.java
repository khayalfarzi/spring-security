package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsHashMap {
    private final HashMap<String, String> details = new HashMap<>();

    public UserDetailsHashMap() {
        details.put("admin", "admin");
        details.put("xx", "xx");
    }

    private UserDetails mapper(Map.Entry<String, String> entry) {
        return User.withDefaultPasswordEncoder()
                .username(entry.getKey())
                .password(entry.getValue())
                .roles("USER")
                .build();
    }


    public UserDetailsService udsHashMap() {
        Collection<UserDetails> data = details.entrySet()
                .stream()
                .map(this::mapper)
                .collect(Collectors.toSet());

        return new InMemoryUserDetailsManager(data);
    }
}
