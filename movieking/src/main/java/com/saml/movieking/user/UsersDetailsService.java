package com.saml.movieking.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsersDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
