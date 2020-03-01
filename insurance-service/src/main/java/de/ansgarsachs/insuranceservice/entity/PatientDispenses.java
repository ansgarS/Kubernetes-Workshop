package de.ansgarsachs.insuranceservice.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 * PatientDispenses
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
@Entity
@Proxy(lazy = false)
@Cacheable(value = false)
@Table(name = "dispenses", schema = "public")
public class PatientDispenses {
    @Id
    @Column(name = "id")
    private String patientId;

    @Column(name = "costs")
    private double costs;

    @Column(name = "prescription_count")
    private int prescriptionCount;

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
