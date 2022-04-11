package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.role.UserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @Column(name="MIDDLE")
    private Character middle;

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

    @OneToOne
    @JoinColumn(name = "VACCINATION_STATUS_ID")
    private VaccinationStatus vaccinationStatus;

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

    public Character getMiddle() {
        return middle;
    }

    public void setMiddle(Character middle) {
        this.middle = middle;
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

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum Role {
        ROLE_CEO,ROLE_CSRA,ROLE_EMPLOYEE,ROLE_CUSTOMER;

        public static List<String> readOnlyRoles() {
            return List.of(ROLE_CEO.name(),ROLE_EMPLOYEE.name());
        }

        public static String editAllRole(){
            return ROLE_CSRA.name();
        }
    }
}
