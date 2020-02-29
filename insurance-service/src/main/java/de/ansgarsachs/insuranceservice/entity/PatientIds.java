package de.ansgarsachs.insuranceservice.entity;

import java.util.List;

/**
 * PatientIds
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class PatientIds {
    private List<String> patients;

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }

    public List<String> getPatients() {
        return this.patients;
    }

    public String toString() {
        String patientIds = this.patients.stream()
                .reduce("", (acc, curr) ->
                        String.format(acc.length() == 0 ? "'%s'" : "%s,'%s'", acc, curr));

        return String.format(
                "patients{patients=[%s]}",
                patientIds
        );
    }


}
