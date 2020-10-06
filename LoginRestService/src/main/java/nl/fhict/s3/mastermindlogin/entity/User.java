package nl.fhict.s3.mastermindlogin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
}
