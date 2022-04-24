package edu.bowiestate.covidTracker.users;

import org.joda.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class VaccinationRecordId implements Serializable {

    @Column(name = "USER_ID")
    private long userId;

    @Column(name = "VACCINATION_DATE")
    private Date vaccinationDate;

    @Column(name = "VACCINE_TYPE")
    @Enumerated(EnumType.STRING)
    private VaccinationStatus.VaccineType vaccineType;

    public VaccinationRecordId() {

    }

    public VaccinationRecordId(long userId, Date vaccinationDate, VaccinationStatus.VaccineType vaccineType){
        this.userId = userId;
        this.vaccinationDate = vaccinationDate;
        this.vaccineType = vaccineType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public VaccinationStatus.VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccinationStatus.VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VaccinationRecordId that = (VaccinationRecordId) o;
        LocalDate thisDate = new LocalDate(vaccinationDate);
        LocalDate thatDate = new LocalDate(that.vaccinationDate);
        return userId == that.userId && thisDate.equals(thatDate) && vaccineType == that.vaccineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, vaccinationDate, vaccineType);
    }
}
