package edu.bowiestate.covidTracker.users;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "VACCINATION_STATUS")
@EntityListeners(AuditingEntityListener.class)
public class VaccinationStatus {

    @EmbeddedId
    private VaccinationRecordId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    private Date lastModifiedDate;

    public VaccinationStatus() {

    }

    public VaccinationRecordId getId() {
        return id;
    }

    public void setId(VaccinationRecordId id) {
        this.id = id;
    }

    public VaccinationStatus(User user, Date vaccinationDate, VaccineType vaccineType) {
        this.user = user;
        this.id = new VaccinationRecordId(user.getId(), vaccinationDate, vaccineType);
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

    public Date getVaccinationDate() {
        return id.getVaccinationDate();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public enum VaccineType {

        COMIRNATY_PFIZER_DOSE1("Comirnaty/Pfizer-Dose1"),
        COMIRNATY_PFIZER_DOSE2("Comirnaty/Pfizer-Dose1"),
        SPIKEVAX_MODERNA_DOSE1("Spikevax/Moderna-Dose1"),
        SPIKEVAX_MODERNA_DOSE2("Spikevax/Moderna-Dose2");

        private String name;

        VaccineType(String name){
            this.name = name;
        }

    }
}
