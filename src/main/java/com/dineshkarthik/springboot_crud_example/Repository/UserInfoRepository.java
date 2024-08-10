package com.dineshkarthik.springboot_crud_example.Repository;

import com.dineshkarthik.springboot_crud_example.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByName(String username);
}
