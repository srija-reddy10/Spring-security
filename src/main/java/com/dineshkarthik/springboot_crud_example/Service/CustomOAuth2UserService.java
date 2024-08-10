package com.dineshkarthik.springboot_crud_example.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = super.loadUser(userRequest);
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//
//        // Assign authorities based on OAuth2 provider attributes (e.g., GitHub, Google, etc.)
//        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//
//        return new DefaultOAuth2User(authorities, attributes, "id"); // Use the correct attribute for username/id
//    }
@Override
public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    Map<String, Object> attributes = oAuth2User.getAttributes();

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

    // Example condition to add ROLE_ADMIN
    if ("admin".equals(attributes.get("login"))) { // Replace "login" with the appropriate attribute
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    return new DefaultOAuth2User(authorities, attributes, "id"); // Use the correct attribute for username/id
}
}

