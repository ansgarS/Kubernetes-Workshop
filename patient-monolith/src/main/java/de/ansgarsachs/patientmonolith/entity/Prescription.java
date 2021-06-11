package de.ansgarsachs.patientmonolith.entity;

/**
 * Prescription
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class Prescription {
    private String id;
    private String patientId;
    private String name;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return String.format(
                "Prescription{id='%s', patientId='%s', name='%s', price='%s'}",
                id,
                patientId,
                name,
                price
        );
    }
}
