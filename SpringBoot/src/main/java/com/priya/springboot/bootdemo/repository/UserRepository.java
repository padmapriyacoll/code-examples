package com.priya.springboot.bootdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priya.springboot.bootdemo.model.User1;

public interface UserRepository extends JpaRepository<User1,Integer> {

}
