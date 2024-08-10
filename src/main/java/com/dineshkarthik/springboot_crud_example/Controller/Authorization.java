package com.dineshkarthik.springboot_crud_example.Controller;

import com.dineshkarthik.springboot_crud_example.Model.UserInfo;
import com.dineshkarthik.springboot_crud_example.Service.UserInfoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/details")
public class Authorization {
    @Autowired
    private UserInfoUserDetailsService userService;
    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }

}
