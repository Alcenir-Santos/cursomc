package br.com.foxi.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.foxi.security.UserSS;

public class UserService {
    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception ex) {
            return null;
        }
    }
}
