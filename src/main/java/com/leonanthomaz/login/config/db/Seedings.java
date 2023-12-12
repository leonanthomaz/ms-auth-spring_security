package com.leonanthomaz.login.config.db;

import com.leonanthomaz.login.enums.UserRole;
import com.leonanthomaz.login.models.User;
import com.leonanthomaz.login.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@Configuration
@Profile("test")
public class Seedings implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(1L, "Leonan",  "meu_email@gmail.com", new BCryptPasswordEncoder().encode("123"), UserRole.ADMIN);
        User u2 = new User(2L, "Teste",  "teste.teste@gmail.com", new BCryptPasswordEncoder().encode("123"), UserRole.USER);

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
