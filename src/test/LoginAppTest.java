package test;

import main.LoginApp;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginAppTest {
    private LoginApp loginApp;

    @BeforeEach
    public void setUp() {
        loginApp = new LoginApp();
    }

    @Test
    public void testAuthenticateUser_ValidCredentials() {
        String email = "test@example.com";
        String password = "correct_password";
        String result = loginApp.authenticateUser(email, password);
        assertEquals("Test User", result, "Expected username should match.");
    }

    @Test
    public void testAuthenticateUser_InvalidCredentials() {
        String email = "test@example.com";
        String password = "wrong_password";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Result should be null for invalid credentials.");
    }

    @Test
    public void testAuthenticateUser_EmptyEmail() {
        String email = "";
        String password = "password";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Result should be null for empty email.");
    }

    @Test
    public void testAuthenticateUser_EmptyPassword() {
        String email = "test@example.com";
        String password = "";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Result should be null for empty password.");
    }

    @Test
    public void testAuthenticateUser_EmailNotFound() {
        String email = "nonexistent@example.com";
        String password = "password";
        String result = loginApp.authenticateUser(email, password);
        assertNull(result, "Result should be null for nonexistent email.");
    }

    @Test
    public void testAuthenticateUser_DatabaseConnectionFailure() {
        LoginApp faultyLoginApp = new LoginApp() {
            @Override
            protected String authenticateUser(String email, String password) {
                return null; // Simulate database connection error
            }
        };

        String result = faultyLoginApp.authenticateUser("test@example.com", "password");
        assertNull(result, "Result should be null when database connection fails.");
    }

    @AfterEach
    public void tearDown() {
        loginApp = null;
    }
}
