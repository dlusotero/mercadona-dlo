package com.mercadonarest.infraestructure.postgres.service;

import com.mercadonarest.configuration.UserDetailsImpl;
import com.mercadonarest.infraestructure.postgres.daos.UserDAO;
import com.mercadonarest.infraestructure.postgres.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found with username: "+ username));
        return UserDetailsImpl.build(user);
    }
}
