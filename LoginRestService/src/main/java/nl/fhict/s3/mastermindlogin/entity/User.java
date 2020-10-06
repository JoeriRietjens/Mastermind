package nl.fhict.s3.mastermindlogin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String username;
    @JsonIgnore
    private String password; // JsonIgnore makes sure password is not returned

    public User() {}

    public User(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
}
