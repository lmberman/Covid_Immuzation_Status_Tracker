package edu.bowiestate.covidTracker.users;
import javax.validation.constraints.Pattern;

/**
 * Form to accept new user credentials and validate if the credentials are valid
 * Valid Credentials are:
 * 1. Username doesnt exist for an existing user
 * 2. Username is required
 * 3. Password is required length and doesnt contain invalid characters
 */
public class UsernameAvailabilityForm {

    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$", message = "username must be of 8 to 50 length with no special characters")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
