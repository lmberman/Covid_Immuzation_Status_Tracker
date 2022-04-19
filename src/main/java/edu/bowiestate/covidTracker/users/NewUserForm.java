package edu.bowiestate.covidTracker.users;

import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
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

    //@Pattern(regexp = "(^[a-z ,.'-]{1,100}+$/i)", message = "firstname  is invalid")
    private String firstname;

    //@Nullable
    private Character middle;

    //@Pattern(regexp = "(^[a-z ,.'-]{2,100}+$/i)", message = "lastname  is invalid")
    private String lastname;

    //@Pattern(regexp = "^(?=[a-zA-Z0-9._]{2,255}$)", message = "address  is invalid")
    private String address;

    //@Pattern(regexp = "^\\s*\\S+(?:\\s+\\S+){2}$", message = "address2  is invalid")
    private String address2;

    //@Pattern(regexp = "^(?=[a-zA-Z]{2,100}$)", message = "address  is invalid")
    private String city;

    //@Pattern(regexp = "^(?:(A[KLRZ]|C[AOT]|D[CE]|FL|GA|HI|I[ADLN]|K[SY]|LA|M[ADEINOST]|N[CDEHJMVY]|O[HKR]|P[AR]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY]))$", message = "state  is invalid")
    private String state;

    //@Pattern(regexp = "^(?!0{5})(\\d{5})(?!-?0{4})(-?\\d{4})?$", message = "zip  is invalid")
    private String zip;

    //@Pattern(regexp = "^[2-9]\\d{2}-\\d{3}-\\d{4}$", message = "Phone  is invalid")
    private String phone;

    //@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email is invalid")
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
