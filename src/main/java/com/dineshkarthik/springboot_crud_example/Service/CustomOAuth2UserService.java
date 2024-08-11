package com.dineshkarthik.springboot_crud_example.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        Set<GrantedAuthority> authorities = new HashSet<>(oAuth2User.getAuthorities());

        // GitHub user attributes
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String login = (String) attributes.get("login");

        // Log the GitHub username
        logger.info("GitHub login: " + login);

        // Example: Assigning ROLE_ADMIN to a specific GitHub user
        if ("srija-reddy10".equalsIgnoreCase(login)) { // Replace with your GitHub username
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            logger.info("Assigned ROLE_ADMIN to: " + login);
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            logger.info("Assigned ROLE_USER to: " + login);
        }

        return new DefaultOAuth2User(authorities, attributes, "login");
    }
}
