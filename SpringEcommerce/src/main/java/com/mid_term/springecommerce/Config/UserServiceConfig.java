package com.mid_term.springecommerce.Config;

import com.mid_term.springecommerce.Models.User;
import com.mid_term.springecommerce.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.stream.Collectors;

public class UserServiceConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find username");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}
