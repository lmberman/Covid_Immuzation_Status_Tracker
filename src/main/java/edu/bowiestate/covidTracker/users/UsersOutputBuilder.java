package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.vaccinationStatus.VaccinateStatus;
import edu.bowiestate.covidTracker.vaccinationStatus.VaccinationStatus;

import java.util.ArrayList;
import java.util.List;

public class UsersOutputBuilder {
    private Long id;
    private String firstname;
    private String lastname;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    private boolean vaccinated;

    public UsersOutputBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UsersOutputBuilder withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UsersOutputBuilder withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UsersOutputBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UsersOutputBuilder withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public UsersOutputBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public UsersOutputBuilder withState(String state) {
        this.state = state;
        return this;
    }

    public UsersOutputBuilder withZip(String zip) {
        this.zip = zip;
        return this;
    }

    public UsersOutputBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UsersOutputBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UsersOutputBuilder withVaccinated(VaccinateStatus vaccinateStatus) {
        if(vaccinateStatus == VaccinateStatus.Y) {
            this.vaccinated = true;
        }
        return this;
    }

    public List<UsersOutput> buildForCSRAUser(List<User> users) {
        List<UsersOutput> outputs = new ArrayList<>();
        for(User user: users) {
            UsersOutput output = new UsersOutputBuilder()
                    .withId(user.getId())
                    .withFirstname(user.getFirstname())
                    .withLastname(user.getLastname())
                    .withAddress(user.getAddress())
                    .withAddress2(user.getAddress2())
                    .withCity(user.getCity())
                    .withState(user.getState())
                    .withZip(user.getZip())
                    .withPhone(user.getPhone())
                    .withEmail(user.getEmail())
                    .withVaccinated(user.getVaccinationStatus().getVaccinated())
                    .build();
            outputs.add(output);
        }
        return outputs;
    }

    public List<UsersOutput> buildForReadOnlyUser(List<User> users) {
        List<UsersOutput> outputs = new ArrayList<>();
        for(User user: users) {
            UsersOutput output = new UsersOutputBuilder()
                    .withFirstname(user.getFirstname())
                    .withLastname(user.getLastname())
                    .withVaccinated(user.getVaccinationStatus().getVaccinated())
                    .build();
            outputs.add(output);
        }
        return outputs;
    }

    public UsersOutput build() {
        UsersOutput usersOutput = new UsersOutput();
        usersOutput.setId(id);
        usersOutput.setFirstname(firstname);
        usersOutput.setLastname(lastname);
        usersOutput.setAddress(address);
        usersOutput.setAddress2(address2);
        usersOutput.setCity(city);
        usersOutput.setState(state);
        usersOutput.setZip(zip);
        usersOutput.setEmail(email);
        usersOutput.setPhone(phone);
        usersOutput.setVaccinated(vaccinated);
        return usersOutput;
    }

}
