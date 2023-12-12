package com.leonanthomaz.login.DTO.auth;


import com.leonanthomaz.login.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {
}
