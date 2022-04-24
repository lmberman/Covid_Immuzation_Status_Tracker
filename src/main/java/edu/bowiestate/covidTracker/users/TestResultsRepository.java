package edu.bowiestate.covidTracker.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultsRepository extends JpaRepository<TestResult, TestResultId> {
    List<TestResult> findByIdUserId(Long userId);
}

