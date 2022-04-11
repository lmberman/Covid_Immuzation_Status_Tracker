package edu.bowiestate.covidTracker.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class VaccinationStatusRestController {

        @Autowired
        private VaccinationStatusRepository vaccinationStatusRepository;

        @Autowired
        private UsersRepository usersRepository;

        @PostMapping(value = "/immunizationRecords/new")
        public void createUserVaccination(@RequestParam ImmunizationInput immunizationInput, Principal principal){
            User user = usersRepository.findByUsername(principal.getName());

            VaccinationStatus vaccinationStatus = new VaccinationStatus();
            // think vaccinationStatus should link back to user so would have to search for user and link here
            vaccinationStatus.setVaccinated(immunizationInput.isVaccinated()? VaccinateStatus.Y: VaccinateStatus.N);
            vaccinationStatus.setVaccinationDate(immunizationInput.getVaccinationDate());
            user.setVaccinationStatus(vaccinationStatus);
            vaccinationStatusRepository.save(vaccinationStatus);
        }

        @PutMapping(value = "user/vaccinateUpdate")
        public void updateUserStatus(@RequestParam VaccinationStatus vaccinationStatus) {
            vaccinationStatusRepository.save(vaccinationStatus);
        }

        @GetMapping("/immunizationRecords")
        public String getImmunizationRecords(Principal principal){
            // find vaccination records by user
            return "immunizationRecords";
        }


}
