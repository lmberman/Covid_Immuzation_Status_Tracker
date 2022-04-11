package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/testResults")
public class TestResultsController {

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping()
    public String getTestResults(Authentication authentication, Model model) {

        return "testResults";
    }

    @PostMapping("/new")
    public void addNewTestResult(TestResultsInput testResultsInput, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());

        TestResult testResult = new TestResult();
        testResult.setUser(user);
        testResult.setTestDate(testResult.getTestDate());
        testResult.setStatus(testResultsInput.getStatus());

        testResultsRepository.save(testResult);
    }
}
