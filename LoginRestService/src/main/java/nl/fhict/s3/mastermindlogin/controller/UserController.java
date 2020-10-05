package nl.fhict.s3.mastermindlogin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.fhict.s3.mastermindlogin.entity.User;
import nl.fhict.s3.mastermindlogin.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    public User getTestUser() {
        return new User("testUsername","testPassword");
    }
    
    @PostMapping
    public User postUser(@RequestBody User user) {
        return repository.save(user);
    }
}
