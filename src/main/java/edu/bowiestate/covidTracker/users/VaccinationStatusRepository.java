package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.users.VaccinationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationStatusRepository extends JpaRepository<VaccinationStatus, Long> {
}
