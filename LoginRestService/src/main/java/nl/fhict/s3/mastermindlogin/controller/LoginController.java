package nl.fhict.s3.mastermindlogin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.fhict.s3.mastermindlogin.entity.User;
import nl.fhict.s3.mastermindlogin.exception.UserNotFoundException;
import nl.fhict.s3.mastermindlogin.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
    private UserRepository repository;

    public LoginController(UserRepository repository) {
        this.repository = repository;
    }
    
    @PostMapping
    public User login(@RequestBody User user) {
        String postedUsername = user.getUsername();
        String postedPassword = user.getPassword();

        User validUser = repository.findByUsername(postedUsername).orElseThrow(UserNotFoundException::new);

        if(validUser.getPassword().equals(postedPassword)) {
            return validUser;
        }

        return null;
    }
}
