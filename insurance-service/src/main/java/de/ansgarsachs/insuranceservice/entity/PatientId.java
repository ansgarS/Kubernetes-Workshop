package de.ansgarsachs.insuranceservice.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;

/**
 * PatientIds
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
@Entity
@Cacheable(value = false)
@Proxy(lazy = false)
@Table(name = "patient_ids", schema = "public")
public class PatientId {
    @Id
    @Column(name = "id")
    private String id;

    public String toString() {
        return String.format(
                "PatientId{id=%s}",
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
