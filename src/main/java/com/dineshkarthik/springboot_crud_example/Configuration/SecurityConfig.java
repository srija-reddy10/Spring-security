package com.dineshkarthik.springboot_crud_example.Configuration;

import com.dineshkarthik.springboot_crud_example.Service.CustomOAuth2UserService;
import com.dineshkarthik.springboot_crud_example.Service.UserInfoUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    //authentication
    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withUsername("Basant")
//                .password(encoder.encode("Pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("John")
//                .password(encoder.encode("Pwd2"))
//                .roles("USER","ADMIN","HR")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetailsService();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("crudOperations/welcome","/details/new").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/crudOperations/**")
//                .authenticated().and().formLogin().and().httpBasic()
//                .and().build();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("crudOperations/welcome","/details/new").permitAll(); // Public access to specific endpoints
//                    auth.requestMatchers("/crudOperations/products","/crudOperations/productById/{id}","/crudOperations/productByName","/crudOperations/update","/crudOperations/delete/{id}").authenticated(); // Require authentication for /products/** endpoints
//                    auth.anyRequest().authenticated(); // Require authentication for any other requests
//                })
//                .formLogin(withDefaults())  // Default form-based login
//                .oauth2Login(withDefaults()) // Default OAuth2 login
//                .build();
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests(auth -> {
//                    auth.requestMatchers("/crudOperations/welcome", "/details/new").permitAll();
//                    auth.requestMatchers("/crudOperations/products", "/crudOperations/productById/{id}", "/crudOperations/productByName", "/crudOperations/update", "/crudOperations/delete/{id}")
//                            .hasAuthority("ROLE_USER"); // Ensure this matches the roles granted by OAuth2 login
//                    auth.anyRequest().authenticated();
//                }).httpBasic(withDefaults()) // Adding HTTP Basic authentication
//                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint().userService(oAuth2UserService())) // Custom OAuth2 user service
//                .formLogin(withDefaults())
//                .build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/crudOperations/welcome", "/details/new").permitAll();
                    auth.requestMatchers("/crudOperations/products", "/crudOperations/productById/{id}", "/crudOperations/productByName")
                            .hasAuthority("ROLE_USER"); // Ensure this matches the roles granted by OAuth2 login
                    auth.requestMatchers("/crudOperations/update", "/crudOperations/delete/{id}")
                            .hasAuthority("ROLE_ADMIN"); // Ensure this matches the roles granted by OAuth2 login
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint().userService(oAuth2UserService())) // Custom OAuth2 user service
                .formLogin(withDefaults())
                .httpBasic(withDefaults()) // Allow HTTP Basic authentication
                .build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        return new CustomOAuth2UserService(); // Replace with your implementation
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
