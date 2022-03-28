package edu.bowiestate.covidTracker.vaccinationStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VaccinationStatusRestController {

        @Autowired
        private VaccinationStatusRepository vaccinationStatusRepository;

        @PostMapping(value = "user/vaccinate")
        public void createUserVaccination(@RequestParam VaccinationStatus vaccinationStatus){
            vaccinationStatusRepository.save(vaccinationStatus);
        }

        @PutMapping(value = "user/vaccinateUpdate")
        public void updateUserStatus(@RequestParam VaccinationStatus vaccinationStatus) {
            vaccinationStatusRepository.save(vaccinationStatus);
        }
}
