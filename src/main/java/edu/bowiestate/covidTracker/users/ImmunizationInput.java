package edu.bowiestate.covidTracker.users;

import java.time.LocalDate;

public class ImmunizationInput {
    private boolean vaccinated;
    private LocalDate vaccinationDate;

    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public LocalDate getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(LocalDate vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }
}
