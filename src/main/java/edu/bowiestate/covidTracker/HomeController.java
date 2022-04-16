package edu.bowiestate.covidTracker;

import edu.bowiestate.covidTracker.users.TestResultsRepository;
import edu.bowiestate.covidTracker.users.User;
import edu.bowiestate.covidTracker.users.UsersRepository;
import edu.bowiestate.covidTracker.users.VaccinationStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Autowired
    private VaccinationStatusRepository vaccinationStatusRepository;

    @GetMapping({"/login","/"})
    public String login() {
        return "login";
    }

    @PreAuthorize("hasAnyRole('ROLE_CEO','ROLE_CSRA','ROLE_EMPLOYEE','ROLE_CUSTOMER')")
    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        // check roles and return home page based on role
        Optional<GrantedAuthority> loggedInUserRole = (Optional<GrantedAuthority>) authentication.getAuthorities().stream().findFirst();
        if(loggedInUserRole.isPresent()) {
            String role = loggedInUserRole.get().getAuthority();
            if(role.equals(User.Role.ROLE_CUSTOMER.name())){
                User user = usersRepository.findByUsername(authentication.getName());
                model.addAttribute("name", user.getFirstname());
                model.addAttribute("testRecords", testResultsRepository.findByUserId(user.getId()));
                model.addAttribute("vaccinationRecords", vaccinationStatusRepository.findByUserId(user.getId()));
                return "home";
            } else {
                model.addAttribute("users",usersRepository.findAll());
                // get table of user with firstname and lastname
                // add links for test results and vaccination records
                // optional contact information link for CSRA
                // optionally add test results, vaccination records, and contact information for CSRAs
                return "adminHome";
            }
        }
        return null;
    }

}
