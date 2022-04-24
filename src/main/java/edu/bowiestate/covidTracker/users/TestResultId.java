package edu.bowiestate.covidTracker.users;

import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class TestResultId implements Serializable {

   @Column(name = "USER_ID")
   private long userId;

   @Column(name = "TEST_DATE")
   private Date testDate;

    public TestResultId() {

    }

    public TestResultId(long userId, Date testDate) {
        this.userId = userId;
        this.testDate = testDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUser(long userId) {
        this.userId = userId;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestResultId that = (TestResultId) o;
        LocalDate thisDate = new LocalDate(testDate);
        LocalDate thatDate = new LocalDate(that.testDate);

        return userId == that.userId && thisDate.equals(thatDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, testDate);
    }
}
