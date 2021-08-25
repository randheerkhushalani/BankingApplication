package com.example.bankingapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingapplication.model.UserLoginDetails;

public interface UserRepository extends JpaRepository < UserLoginDetails, Integer > {
    Optional<UserLoginDetails> findByUserName(String userName);
}