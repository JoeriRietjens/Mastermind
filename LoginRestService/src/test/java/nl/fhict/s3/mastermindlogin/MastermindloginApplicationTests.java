package nl.fhict.s3.mastermindlogin;

import nl.fhict.s3.mastermindlogin.controller.UserController;
import nl.fhict.s3.mastermindlogin.entity.User;
import nl.fhict.s3.mastermindlogin.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MastermindLoginApplicationTests {
    private static final String TEST_USERNAME = "testUsername";
    private static final String TEST_PASSWORD = "testPassword";

    @Autowired
    private UserRepository userRepository;
    private UserController userController;

    @BeforeEach
    void loadContext() {
        userController = new UserController(userRepository);
    }

    @Test
    void contextLoads() {
        assertNotNull(userRepository);
    }

    @Test
    void getTestUser() {
        User testUser = userController.getTestUser();

        assertEquals(TEST_USERNAME, testUser.getUsername());
        assertEquals(TEST_PASSWORD, testUser.getPassword());
    }
}
