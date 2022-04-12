package edu.bowiestate.covidTracker.users;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TestResultsInput {

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date testDate;
    private char status;

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
