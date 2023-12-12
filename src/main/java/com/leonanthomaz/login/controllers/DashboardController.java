package com.leonanthomaz.login.controllers;

import com.leonanthomaz.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dashboard")
public class DashboardController {

    @Autowired
    private UserService service;

    //Página inicial - apenas com usuários com token válido
    @GetMapping
    public ResponseEntity dashboard(){
        return ResponseEntity.ok().body("PÁGINA INICIAL");
    }
}
