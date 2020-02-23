package de.ansgarsachs.patientmonolith.entity;

/**
 * Patient
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class Patient {
    private String id;
    private String firstName;
    private String lastName;
    private int age;

    public String toString() {
        return String.format(
                "Patient{id='%s', firstName='%s', lastName='%s', age='%s'}",
                id,
                firstName,
                lastName,
                age
        );
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
