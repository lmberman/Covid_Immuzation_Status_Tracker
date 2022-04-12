package edu.bowiestate.covidTracker.users;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ImmunizationInput {

    private boolean vaccinated;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date vaccinationDate;

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public Date getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
