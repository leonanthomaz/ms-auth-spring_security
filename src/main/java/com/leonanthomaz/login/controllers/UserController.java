package com.leonanthomaz.login.controllers;

import com.leonanthomaz.login.models.User;
import com.leonanthomaz.login.repositories.UserRepository;
import com.leonanthomaz.login.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repository;

    //Buscando usuário por ID
    @RequestMapping("{id}")
    public ResponseEntity findById(@PathVariable Long id) throws Exception {
       return service.findById(id).map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Deletando usuário por ID
    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        return ResponseEntity.accepted().body(service.deleteById(id));
    }

    //Atualizando usuário
    @PutMapping("{id}")
    public ResponseEntity putById(@PathVariable("id") Long id, @RequestBody @Valid User data) throws Exception {
        User oldUser = service.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(oldUser != null){
            User newUser = new User(data.getName(), data.getEmail());
            repository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(data);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao atualizar usuário");
        }
    }

}
