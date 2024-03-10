package cgg.spring_security.spring_security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cgg.spring_security.spring_security.entities.CustomUserDetails;
import cgg.spring_security.spring_security.entities.User;
import cgg.spring_security.spring_security.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byUsername = repo.findByUsername(username);

        if (byUsername == null) {
            throw new UsernameNotFoundException("no username found");

        }

        return new CustomUserDetails(byUsername);
    }

}
