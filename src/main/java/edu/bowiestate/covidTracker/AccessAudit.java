package edu.bowiestate.covidTracker;

import edu.bowiestate.covidTracker.users.User;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Class to audit all access points in the application including login failures and success
 */
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name="ACCESS_AUDIT")
public class AccessAudit {

    @Id
    @TableGenerator(name = "Access_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 1, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Access_Gen")
    @Column(name="ID")
    private long id;

    @Column(name = "ACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @JoinColumn(name = "FOR_USER")
    private String forUser;

    @JoinColumn(name = "PERFORMED_BY_USER")
    private String performedByUser;

    @CreatedDate
    private Date performedOn;

    public AccessAudit() {

    }

    public AccessAudit(ActionType actionType,String forUser, String performedBy) {
        this.actionType = actionType;
        this.forUser = forUser;
        this.performedByUser = performedBy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActionType() {
        return actionType.name();
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public String getForUser() {
        return forUser;
    }

    public void setForUser(String forUser) {
        this.forUser = forUser;
    }

    public String getPerformedByUser() {
        return performedByUser;
    }

    public void setPerformedByUser(String performedByUser) {
        this.performedByUser = performedByUser;
    }

    public Date getPerformedOn() {
        return performedOn;
    }

    public void setPerformedOn(Date performedOn) {
        this.performedOn = performedOn;
    }

    public enum ActionType {
        LOGIN,LOGOUT,CREATE_USER,ADD_TEST,ADD_VACCINE,UPDATE_VACCINE,VIEW_TEST,VIEW_VACCINE,VIEW_CONTACT;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessAudit that = (AccessAudit) o;
        return id == that.id && actionType.name().equals(that.actionType.name()) && Objects.equals(forUser, that.forUser) && performedByUser.equals(that.performedByUser) && performedOn.equals(that.performedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actionType, forUser, performedByUser, performedOn);
    }
}
