package com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class UserDetailsHashMapEncoded {
    private final HashMap<String, String> details = new HashMap<>();
    private final PasswordEncoder encoder;

    public UserDetailsHashMapEncoded(PasswordEncoder encoder) {
        this.encoder = encoder;
        details.put("admin", "admin");
        details.put("xx", "xx");
    }

    private UserDetails mapper(Map.Entry<String, String> entry) {
        return User
                .withUsername(entry.getKey())
                .password(entry.getValue())
                .passwordEncoder(encoder::encode)
                .roles("USER").build();
    }

    @Bean
    public UserDetailsService service() {
        Collection<UserDetails> data = details.entrySet()
                .stream()
                .map(this::mapper)
                .collect(Collectors.toSet());
        return new InMemoryUserDetailsManager(data);
    }
}
