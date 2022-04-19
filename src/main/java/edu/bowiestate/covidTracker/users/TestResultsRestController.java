package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class TestResultsRestController {

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PreAuthorize("hasAnyRole('ROLE_CSRA','ROLE_CEO','ROLE_EMPLOYEE')")
    @GetMapping("/user/{id}/testResults")
    public String getImmunizationRecords(@PathVariable("id") long id, Model model) {
        if(!usersRepository.findById(id).isPresent()){
            model.addAttribute("error", "User unknown");
            return "adminHome";
        } else {
            model.addAttribute("testResults",testResultsRepository.findByUserId(id));
            return "adminViewUserTestResultRecords";
        }

    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/user/testResults/new")
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
