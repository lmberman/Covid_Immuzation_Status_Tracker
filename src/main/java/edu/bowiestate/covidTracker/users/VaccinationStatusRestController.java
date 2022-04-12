package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class VaccinationStatusRestController {

        @Autowired
        private VaccinationStatusRepository vaccinationStatusRepository;

        @Autowired
        private UsersRepository usersRepository;

        @PreAuthorize("hasRole('ROLE_CUSTOMER')")
        @PostMapping(value = "/user/immunizationRecords/new")
        @ResponseBody
        public void createUserVaccination(ImmunizationInput immunizationInput, Principal principal){
            User user = usersRepository.findByUsername(principal.getName());

            VaccinationStatus vaccinationStatus = new VaccinationStatus();
            // think vaccinationStatus should link back to user so would have to search for user and link here
            vaccinationStatus.setVaccinated(immunizationInput.isVaccinated()? VaccinateStatus.Y: VaccinateStatus.N);
            vaccinationStatus.setVaccinationDate(immunizationInput.getVaccinationDate());

            user.setVaccinationStatus(vaccinationStatus);
            vaccinationStatusRepository.save(vaccinationStatus);
            usersRepository.save(user);
        }

        @PutMapping(value = "/user/vaccinateUpdate")
        public void updateUserStatus(@RequestParam VaccinationStatus vaccinationStatus) {
            vaccinationStatusRepository.save(vaccinationStatus);
        }

        @PreAuthorize("hasRole('ROLE_CUSTOMER')")
        @GetMapping("/user/immunizationRecords")
        public String getImmunizationRecords(Principal principal){
            // find vaccination records by user
            return "immunizationRecords";
        }


}
