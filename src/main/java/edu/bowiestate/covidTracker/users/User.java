package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.role.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="USERS")
public class User implements Serializable {

    @Id
    @TableGenerator(name = "User_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 5, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "User_Gen")
    @Column(name="ID")
    private long id;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="LASTNAME")
    private String lastname;

    @Column(name="USERNAME")
    private String username;

    @Column(name ="PASSWORD")
    private String password;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "ADDRESS2")
    private String address2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "PHONE", length = 12)
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private UserRole userRole;

    // add created date and last modified date to this table

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && firstname.equals(user.firstname) && lastname.equals(user.lastname) && username.equals(user.username) && password.equals(user.password) && address.equals(user.address) && Objects.equals(address2, user.address2) && city.equals(user.city) && state.equals(user.state) && zip.equals(user.zip) && phone.equals(user.phone) && email.equals(user.email) && userRole.equals(user.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, username, password, address, address2, city, state, zip, phone, email, userRole);
    }

    public enum Role {
        ROLE_CEO("CEO"),
        ROLE_CSRA("CSRA"),
        ROLE_EMPLOYEE("EMPLOYEE"),
        ROLE_CUSTOMER("CUSTOMER");

        private String nameWithoutPrefix;

        Role(String nameWithoutPrefix) {
            this.nameWithoutPrefix = nameWithoutPrefix;
        }

        public static List<String> readOnlyRoles() {
            return List.of(ROLE_CEO.name(),ROLE_EMPLOYEE.name());
        }

        public static String editAllRole(){
            return ROLE_CSRA.name();
        }

        public String getNameWithoutPrefix() {
            return nameWithoutPrefix;
        }

        public void setNameWithoutPrefix(String nameWithoutPrefix) {
            this.nameWithoutPrefix = nameWithoutPrefix;
        }
    }
}
