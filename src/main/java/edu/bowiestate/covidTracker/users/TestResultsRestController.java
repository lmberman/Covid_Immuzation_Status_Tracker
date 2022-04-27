package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class TestResultsRestController {

    @Autowired
    private TestResultsRepository testResultsRepository;

    @Autowired
    private UsersRepository usersRepository;

    private SimpleDateFormat formatter = new SimpleDateFormat(
            "dd/MM/yyyy");

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/user/testResults")
    public String getImmunizationRecords(Model model) {
        model.addAttribute("today", new Date());
        return "testResults";
    }

    @PreAuthorize("hasAnyRole('ROLE_CSRA','ROLE_CEO','ROLE_EMPLOYEE')")
    @GetMapping("/user/{id}/testResults")
    public String getImmunizationRecords(@PathVariable("id") long id, Model model) {
        if (!usersRepository.findById(id).isPresent()) {
            model.addAttribute("error", "User unknown");
            return "adminHome";
        } else {
            model.addAttribute("testRecords", testResultsRepository.findByIdUserId(id));
            return "adminViewUserTestResultRecords";
        }

    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/user/testResults/new")
    public String addNewTestResult(TestResultsInput testResultsInput, Principal principal, Model model) {
        User user = usersRepository.findByUsername(principal.getName());
        if(testResultsInput.getStatus() == 'P' || testResultsInput.getStatus() == 'N'){
            TestResultId testResultId = new TestResultId(user.getId(), testResultsInput.getTestDate());
            Optional<TestResult> existingTestResult = testResultsRepository.findById(testResultId);
            if(existingTestResult.isPresent() && existingTestResult.get().getStatusChar() == testResultsInput.getStatus()){
                model.addAttribute("recordAlreadyExists", true);
                return "testResults";
                // show warning test result already exists
            }
        }
        if (testResultsRepository.findByIdUserId(user.getId()).stream()
                .filter(result -> formatter.format(result.getCreatedDate()).equals(formatter.format(new Date())))
                .collect(Collectors.toList())
                .size() < 4) {
            //if less than 4 test results created today by the user then allow new one
            if (testResultsInput.getStatus() == 'U'
                    || testResultsInput.getStatus() == 'P'
                    || testResultsInput.getStatus() == 'N') {
                TestResultId testResultId = new TestResultId(user.getId(), testResultsInput.getTestDate());
                Optional<TestResult> existingTestResult = testResultsRepository.findById(testResultId);
                if(existingTestResult.isPresent()){
                    existingTestResult.get().setStatus(testResultsInput.getStatus());
                    testResultsRepository.save(existingTestResult.get());
                } else {
                    testResultsRepository.save(new TestResult(user, testResultsInput.getStatus(), testResultsInput.getTestDate()));
                }
                model.addAttribute("success", true);
            } else {
                // or throw error
            }

        } else {
            model.addAttribute("dailyLimitExceeded", true);
        }
        return "testResults";


    }
}
