package edu.bowiestate.covidTracker.users;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Output pojo for Users returned from the api
 * Information is populated on a need to know basis
 */
public class UserContactOutput {

    private String firstname;
    private String lastname;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
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

        return address == null? "": address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {

        return address2 == null? "": address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {

        return city == null ? "": city;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
