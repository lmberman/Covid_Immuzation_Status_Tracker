package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.role.UserRole;
import edu.bowiestate.covidTracker.role.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserRestController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

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
    public String createUser(NewUserForm userForm, Model model, Errors errors) {
        if(errors.hasErrors()) {
            return "signup";
        }
        UserRole userRole = userRoleRepository.findByRole("CUSTOMER");
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
        newUser.setUserRole(userRole);

        usersRepository.save(newUser);
        return "signup";
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_CSRA','ROLE_EMPLOYEE')")
    @GetMapping("/singleSearch")
    @ResponseBody
    public UsersOutput findSingleUserByName(@RequestParam String firstname, @RequestParam String lastname, Authentication authentication) {
        Optional<GrantedAuthority> loggedInUserRole = (Optional<GrantedAuthority>) authentication.getAuthorities().stream().findFirst();
        if (loggedInUserRole.isPresent()) {
            User user = usersRepository.findByFirstnameAndLastname(firstname, lastname);
            if (User.Role.readOnlyRoles().contains(loggedInUserRole.get().getAuthority())) {
                return new UsersOutputBuilder().buildForReadOnlyUser(user);
            } else if (User.Role.editAllRole().equals(loggedInUserRole.get().getAuthority())) {
                return new UsersOutputBuilder().buildForCSRAUser(user);
            }
        }
        return null;
    }
}