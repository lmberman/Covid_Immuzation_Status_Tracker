package edu.bowiestate.covidTracker.users;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class TestResultsInput {

//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate testDate;
    private char status;

    public LocalDate getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
