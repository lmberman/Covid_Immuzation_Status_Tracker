package edu.bowiestate.covidTracker.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationStatusRepository extends JpaRepository<VaccinationStatus, Long> {
    List<VaccinationStatus> findByUserId(Long userId);
}
