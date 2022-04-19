package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.role.UserRole;
import edu.bowiestate.covidTracker.role.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("users")
public class UserRestController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String signup(Model model){
        model.addAttribute("userForm", new NewUserForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@Valid NewUserForm userForm, Model model, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "signup";
        }

        UserRole userRole = userRoleRepository.findByRole("CUSTOMER");
        User newUser = new User();
        newUser.setUsername(userForm.getUsername());
        newUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
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
        model.addAttribute("signupSuccess", true);
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_CSRA')")
    @GetMapping("/{id}/contact")
    public String findSingleUserByName(@PathVariable("id") Long id, Model model) {
        Optional<User> user = usersRepository.findById(id);
        if(!user.isPresent()){
            model.addAttribute("error", "User unknown");
            return "adminHome";
        } else {
            User existingUser = user.get();
            UserContactOutput userContactOutput = new UserContactOutput();
            userContactOutput.setFirstname(existingUser.getFirstname());
            userContactOutput.setLastname(existingUser.getLastname());
            userContactOutput.setAddress(existingUser.getAddress());
            userContactOutput.setAddress2(existingUser.getAddress2());
            userContactOutput.setCity(existingUser.getCity());
            userContactOutput.setState(existingUser.getState());
            userContactOutput.setZip(existingUser.getZip());
            userContactOutput.setPhone(existingUser.getPhone());
            userContactOutput.setEmail(existingUser.getEmail());
            model.addAttribute("contact",userContactOutput);
            return "adminViewUserContactInfo";
        }
    }
}
