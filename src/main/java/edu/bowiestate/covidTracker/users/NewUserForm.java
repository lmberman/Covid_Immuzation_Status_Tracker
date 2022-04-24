package edu.bowiestate.covidTracker.users;

import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Form to accept new user information
 */
public class NewUserForm {

    @Pattern(regexp = "^(?=[a-zA-Z0-9._]{8,50}$)(?!.*[_.]{2})[^_.].*[^_.]$", message = "username must be of 8 to 50 length with no special characters")
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$",
            message = "password must contain at least 1 uppercase, 1 lowercase, 1 digit and between 8 and 30 characters ")
    private String password;

    @Pattern(regexp = "^([A-Za-z]+){2,50}$", message = "firstname can contain uppercase/lowercase letters between 1 and 50 characters")
    private String firstname;

    @Pattern(regexp = "^([A-Za-z]+(((\\'|\\-|\\.)?([A-Za-z])+))?){2,50}$", message = "Lastname can be 2-50 characters with .'-,")
    private String lastname;

    @NotEmpty
    @Pattern(regexp = "^([\\d]+[A-Za-z0-9\\s,\\. ]+){2,50}$", message = "Address can be 2-150 characters and contain letters, numbers, and spaces")
    private String address;

    @Pattern(regexp = "^([A-Za-z0-9\\s,\\. ]+[\\d]+){0,50}$", message = "Address2 can be 0-50 characters and contain letters, numbers, and spaces")
    private String address2;

    @Pattern(regexp = "^([A-Za-z]+((( |\\'|\\-|\\.)?([A-Za-z])+))?){2,50}$", message = "city format  is invalid")
    private String city;

    @Pattern(regexp = "^([A-Za-z]){2}$", message = "state  is invalid")
    private String state;

    @Pattern(regexp = "^([0-9]{5})$", message = "zip  is invalid")
    private String zip;

    @Pattern(regexp = "^[2-9]\\d{2}-\\d{3}-\\d{4}$", message = "Phone format incorrect. Expecting format XXX-XXX-XXXX")
    private String phone;

    @Email(message = "Email is invalid")
    private String email;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
