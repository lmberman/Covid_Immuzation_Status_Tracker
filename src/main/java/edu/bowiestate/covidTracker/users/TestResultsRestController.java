package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user/testResults")
public class TestResultsRestController {

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping()
    public String getTestResults(Authentication authentication, Model model) {

        return "testResults";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/new")
    public String addNewTestResult(TestResultsInput testResultsInput, Principal principal, Model model) {
        User user = usersRepository.findByUsername(principal.getName());

        if(testResultsInput.getStatus() == 'U'
                || testResultsInput.getStatus() == 'P'
                || testResultsInput.getStatus() == 'N') {
            TestResult testResult = new TestResult();
            testResult.setUser(user);
            testResult.setTestDate(testResultsInput.getTestDate());
            testResult.setStatus(testResultsInput.getStatus());
            testResultsRepository.save(testResult);
            model.addAttribute("success", true);
        } else {
            // or throw error
        }
        return "testResults";
    }
}
