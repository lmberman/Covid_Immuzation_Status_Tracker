package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public void addNewTestResult(TestResultsInput testResultsInput, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());

        TestResult testResult = new TestResult();
        testResult.setUser(user);
        testResult.setTestDate(testResultsInput.getTestDate());
        testResult.setStatus(testResultsInput.getStatus());

        testResultsRepository.save(testResult);
    }
}
