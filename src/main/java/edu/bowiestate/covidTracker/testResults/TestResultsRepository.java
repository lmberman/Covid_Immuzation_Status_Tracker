package edu.bowiestate.covidTracker.testResults;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestResultsRepository extends JpaRepository<TestResult, Long> {
}
