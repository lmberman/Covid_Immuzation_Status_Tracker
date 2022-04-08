package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.vaccinationStatus.VaccinateStatus;
import org.h2.engine.UserBuilder;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
@RequestMapping("users")
public class UserRestController {

    @Autowired
    private UsersRepository usersRepository;

    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_CSRA','ROLE_EMPLOYEE')")
    @GetMapping(value = "/all")
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

    @GetMapping("/new")
    public String signup(){
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@Valid NewUserForm userForm, Model model, Errors errors) {
        if(errors.hasErrors()) {
            return "signup";
        }
        User newUser = new User();
        newUser.setUsername(userForm.getUsername());
        newUser.setPassword(userForm.getPassword());
        newUser.setFirstname(userForm.getFirstname());
        newUser.setMiddle(userForm.getMiddle());
        newUser.setLastname(userForm.getLastname());
        newUser.setAddress(userForm.getAddress());
        newUser.setAddress2(userForm.getAddress2());
        newUser.setCity(userForm.getCity());
        newUser.setState(userForm.getState());
        newUser.setZip(userForm.getZip());
        newUser.setPhone(userForm.getPhone());
        newUser.setEmail(userForm.getEmail());

        usersRepository.save(newUser);
        return "signup";
    }

    @PutMapping
    public void updateUser(@RequestParam User user) {
        usersRepository.save(user);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam User user) {
        usersRepository.delete(user);
    }
}
