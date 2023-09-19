package com.sp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
