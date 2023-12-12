package com.leonanthomaz.login.services;

import com.leonanthomaz.login.models.User;
import com.leonanthomaz.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> findById(Long id) throws Exception {
        return repository.findById(id);
    }

    public String deleteById(Long id){
        repository.deleteById(id);
        return "Usuário deletado com sucesso!";
    }

}
