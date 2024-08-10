package com.dineshkarthik.springboot_crud_example.Service;

import com.dineshkarthik.springboot_crud_example.Configuration.UserInfoUserDetails;
import com.dineshkarthik.springboot_crud_example.Model.UserInfo;
import com.dineshkarthik.springboot_crud_example.Repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        // we have to convert userInfo object to userDetails object
        return userInfo.map(UserInfoUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found "+username));
//        return userInfo.map(UserInfoUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }

}
