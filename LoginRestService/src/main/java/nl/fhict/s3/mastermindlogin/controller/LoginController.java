package nl.fhict.s3.mastermindlogin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.fhict.s3.mastermindlogin.repository.UserRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/login")
public class LoginController {
    private UserRepository repository;

    public LoginController(UserRepository repository) {
        this.repository = repository;
    }
    
}
