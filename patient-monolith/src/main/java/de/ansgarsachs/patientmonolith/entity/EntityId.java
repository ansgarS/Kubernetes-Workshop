package de.ansgarsachs.patientmonolith.entity;

/**
 * PatientId
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class PatientId {
    private String id;

    public String toString() {
        return String.format(
                "PatientId{id='%s'}",
                id
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
