package de.ansgarsachs.patientservice.entity;

/**
 * EntityId
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class EntityId {
    private String id;

    public String toString() {
        return String.format(
                "Id{id='%s'}",
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
