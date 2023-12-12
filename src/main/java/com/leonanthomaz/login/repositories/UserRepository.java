package com.leonanthomaz.login.repositories;

import com.leonanthomaz.login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByEmail(String email);
}
