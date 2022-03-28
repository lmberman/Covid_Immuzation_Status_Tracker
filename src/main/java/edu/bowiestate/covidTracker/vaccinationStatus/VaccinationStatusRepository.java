package edu.bowiestate.covidTracker.vaccinationStatus;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationStatusRepository extends JpaRepository<VaccinationStatus, Long> {
}
