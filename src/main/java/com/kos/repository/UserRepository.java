package com.kos.repository;

import com.kos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);



}
