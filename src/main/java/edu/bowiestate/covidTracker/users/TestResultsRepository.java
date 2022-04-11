package edu.bowiestate.covidTracker.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultsRepository extends JpaRepository<TestResult, Long> {
}
