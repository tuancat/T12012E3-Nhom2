package com.sam.lab4.service;

import com.sam.lab4.model.User;
import com.sam.lab4.model.UserRole;
import com.sam.lab4.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("customUserDetailService")
public class CustomeUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    static final Logger logger = LoggerFactory.getLogger(CustomeUserDetailService.class);

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findBySsoId(username);
        logger.info("User : {}", user);
        if(user==null){
            logger.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getSsoId(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole userRole : user.getUserRoleSet()) {
            logger.info("UserRole : {}", userRole);
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getName()));
        }
        logger.info("authorities : {}", authorities);
        return authorities;
    }
}
