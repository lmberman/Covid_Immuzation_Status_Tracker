package edu.bowiestate.covidTracker.users;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ImmunizationInput {

    private VaccinationStatus.VaccineType vaccineType;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date vaccinationDate;

    public VaccinationStatus.VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccinationStatus.VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
