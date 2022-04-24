package edu.bowiestate.covidTracker.users;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TEST_RESULTS")
@EntityListeners(AuditingEntityListener.class)
public class TestResult {

    @EmbeddedId
    private TestResultId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "STATUS")
    private char status;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    private Date lastModifiedDate;

    public TestResult() {

    }

    public TestResult(User user, char status, Date testDate){
        this.user = user;
        this.id = new TestResultId(user.getId(),testDate);
        this.status = status;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public TestResultId getTestResultId() {
        return id;
    }

    public void setTestResultId(TestResultId testResultId) {
        this.id = testResultId;
    }

    public User getUser() {
        return user;
    }

    public String getStatus() {
        switch(status){
            case 'P':
                return "Positive";
            case 'N':
                return "Negative";
            case 'U':
                return "Pending";
            default:
                return null;
        }
    }

    public char getStatusChar() {
        return status;
    }

    public Date getTestDate() {
        return id.getTestDate();
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
