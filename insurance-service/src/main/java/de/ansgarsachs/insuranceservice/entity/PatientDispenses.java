package de.ansgarsachs.insuranceservice.entity;

/**
 * PatientDispenses
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class PatientDispenses {
    private String patientId;
    private double costs;

    public String toString() {
        return String.format(
                "PatientDispenses{patientId='%s', costs=%s}",
                patientId,
                costs
        );
    }


    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
    }
}
