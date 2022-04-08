package edu.bowiestate.covidTracker.users;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * Form to accept new user information
 */
public class NewUserForm {

    @Pattern(regexp = "^[a-zA-Z0-9]{5,50}$", message = "username must be of 8 to 50 length with no special characters")
    private String username;

    @Pattern(regexp = "^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])){8,30}$",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
    private String password;

    @Pattern(regexp = "^[a-zA-Z]{1,100}", message = "firstname  is invalid")
    private String firstname;

    @Nullable
    private Character middle;

    @Pattern(regexp = "^[a-zA-Z]{2,100}", message = "lastname  is invalid")
    private String lastname;

    @Pattern(regexp = "^[a-zA-Z0-9 ]{2,255}", message = "address  is invalid")
    private String address;

    @Pattern(regexp = "^[a-zA-Z0-9 #]{0,50}", message = "address2  is invalid")
    private String address2;

    @Pattern(regexp = "^[a-zA-Z ]{2,100}", message = "city  is invalid")
    private String city;

    @Pattern(regexp = "^[a-zA-Z ]{2,100}", message = "state  is invalid")
    private String state;

    @Pattern(regexp = "^[0-9 -]{5,10}$" , message = "Zip  is invalid")
    private String zip;

    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$", message = "Phone  is invalid")
    private String phone;

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]" +
            "+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$", message = "Email is invalid")
    private String email;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Nullable
    public Character getMiddle() {
        return middle;
    }

    public void setMiddle(@Nullable Character middle) {
        this.middle = middle;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Nullable
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(@Nullable String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
