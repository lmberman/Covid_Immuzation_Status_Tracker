package edu.bowiestate.covidTracker.users;

import edu.bowiestate.covidTracker.vaccinationStatus.VaccinateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
