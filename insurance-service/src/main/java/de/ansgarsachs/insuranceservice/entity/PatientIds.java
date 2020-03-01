package de.ansgarsachs.insuranceservice.entity;

import java.util.List;

/**
 * PatientIds
 *
 * <p>
 * TODO: Write a description
 * </p>
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 01.03.20
 */
public class PatientIds {
    private List<String> patients;

    public String toString() {
        String patientIds = this.patients.stream()
                .reduce("", (acc, curr) ->
                        String.format(acc.length() == 0 ? "'%s'" : "%s,'%s'", acc, curr));

        return String.format(
                "PatientIds{id=[%s]}",
                patientIds
        );
    }

    public List<String> getPatients() {
        return patients;
    }

    public void setPatients(List<String> patients) {
        this.patients = patients;
    }
}
