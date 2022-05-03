package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.AccessAudit;
import edu.bowiestate.covidTracker.AccessAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class VaccinationStatusRestController{

    @Autowired
    private VaccinationStatusRepository vaccinationStatusRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AccessAuditRepository accessAuditRepository;

    private SimpleDateFormat formatter = new SimpleDateFormat(
            "dd/MM/yyyy");

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping(value = "/user/immunizationRecords")
    public String getImmunizationPage(Model model, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        logAudit(AccessAudit.ActionType.VIEW_VACCINE, user, user);
        model.addAttribute("today", new Date());
        model.addAttribute("vaccineTypes", VaccinationStatus.VaccineType.values());
        return "immunizationRecords";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping(value = "/user/immunizationRecords/new")
    public String createUserVaccination(ImmunizationInput immunizationInput,
                                        Principal principal, Model model) {
        User user = usersRepository.findByUsername(principal.getName());
        VaccinationRecordId vaccinationRecordId = new VaccinationRecordId(user.getId(), immunizationInput.getVaccinationDate(), immunizationInput.getVaccineType());
        Optional<VaccinationStatus> existingVaccinationStatus = vaccinationStatusRepository.findById(vaccinationRecordId);
        if (existingVaccinationStatus.isPresent()) {
            model.addAttribute("recordAlreadyExists", true);
        } else {
            if (vaccinationStatusRepository.findByUserId(user.getId())
                    .stream()
                    .filter(result -> formatter.format(result.getCreatedDate()).equals(formatter.format(new Date())))
                    .collect(Collectors.toList())
                    .size() < 4) {
                vaccinationStatusRepository.save(new VaccinationStatus(user, immunizationInput.getVaccinationDate(), immunizationInput.getVaccineType()));
                logAudit(AccessAudit.ActionType.ADD_VACCINE, user, user);
                model.addAttribute("success", true);
            } else {
                model.addAttribute("dailyLimitExceeded", true);
            }
        }
        model.addAttribute("vaccineTypes", VaccinationStatus.VaccineType.values());
        return "immunizationRecords";
    }

    @PutMapping(value = "/user/vaccinateUpdate")
    public void updateUserStatus(@RequestParam VaccinationStatus vaccinationStatus, Principal principal) {
        User user = usersRepository.findByUsername(principal.getName());
        logAudit(AccessAudit.ActionType.UPDATE_VACCINE, user, user);
        vaccinationStatusRepository.save(vaccinationStatus);
    }

    @PreAuthorize("hasAnyRole('ROLE_CSRA','ROLE_CEO','ROLE_EMPLOYEE')")
    @GetMapping("/user/{id}/immunizationRecords")
    public String getImmunizationRecords(@PathVariable("id") long id, Model model, Principal principal) {
        Optional<User> recordOwner = usersRepository.findById(id);
        if (!recordOwner.isPresent()) {
            model.addAttribute("error", "User unknown");
            return "adminHome";
        } else {
            User user = usersRepository.findByUsername(principal.getName());
            logAudit(AccessAudit.ActionType.VIEW_VACCINE, recordOwner.get(), user);
            model.addAttribute("vaccinationRecords", vaccinationStatusRepository.findByUserId(id));
            return "adminViewUserVaccineRecords";
        }

    }

    private void logAudit(AccessAudit.ActionType actionType, User forUser, User performedBy) {
        AccessAudit audit = new AccessAudit(actionType, forUser.getUsername(), performedBy.getUsername());
        accessAuditRepository.save(audit);
    }
}
