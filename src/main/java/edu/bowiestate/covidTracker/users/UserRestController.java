package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.vaccinationStatus.VaccinateStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class UserRestController {

    @Autowired
    private UsersRepository usersRepository;

    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_CSRA','ROLE_EMPLOYEE')")
    @GetMapping(value = "users/all")
    @ResponseBody
    public List<UsersOutput> getAllUsers(Authentication authentication) {
        Optional<GrantedAuthority> loggedInUserRole = (Optional<GrantedAuthority>) authentication.getAuthorities().stream().findFirst();
        if(loggedInUserRole.isPresent()) {
            if(User.Role.readOnlyRoles().contains(loggedInUserRole.get().getAuthority())){
                return new UsersOutputBuilder().buildForReadOnlyUser(usersRepository.findAll());
            } else if(User.Role.editAllRole().equals(loggedInUserRole.get().getAuthority())) {
                return new UsersOutputBuilder().buildForCSRAUser(usersRepository.findAll());
            }
        }
        return null;
    }

    @PostMapping(value = "user")
    public void createUser(@RequestParam User user){
        usersRepository.save(user);
    }

    @PutMapping(value = "user")
    public void updateUser(@RequestParam User user) {
        usersRepository.save(user);
    }

    @DeleteMapping(value = "user")
    public void deleteUser(@RequestParam User user) {
        usersRepository.delete(user);
    }

//    @GetMapping(value = "user/vaccinated")
//    public List<User> getVaccinatedUsers() {
//        return usersRepository.findAllVaccinationStatusVaccinated(VaccinateStatus.Y);
//    }



}
