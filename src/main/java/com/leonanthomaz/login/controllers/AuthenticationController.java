package com.leonanthomaz.login.controllers;

import com.leonanthomaz.login.DTO.auth.LoginDTO;
import com.leonanthomaz.login.config.token.TokenService;
import com.leonanthomaz.login.models.User;
import com.leonanthomaz.login.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    //Login de usuarios - USER e ADMIN
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        if(token != null) return ResponseEntity.ok().body(token);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Cadastro de novos usuarios - por ADMIN
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User data){
        if(this.repository.findByEmail(data.getEmail()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getName() ,data.getEmail(), encryptedPassword, data.getRole());

        this.repository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
