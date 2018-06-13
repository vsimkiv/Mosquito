package com.softserve.mosquito.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface CustomUserDetailsService extends UserDetailsService {
    public UserDetails loadUserById(Long id);
}
