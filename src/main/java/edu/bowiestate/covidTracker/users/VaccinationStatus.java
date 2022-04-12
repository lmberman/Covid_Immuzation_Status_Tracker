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

    @Id
    @TableGenerator(name = "Vaccination_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", initialValue = 2, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.TABLE,  generator = "Vaccination_Gen")
    private long id;

    @Column(name = "VACCINATED")
    @Enumerated(EnumType.STRING)
    private VaccinateStatus vaccinated;

    @Column(name = "VACCINATION_DATE")
    private Date vaccinationDate;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    private Date lastModifiedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VaccinateStatus getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(VaccinateStatus vaccinated) {
        this.vaccinated = vaccinated;
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
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
