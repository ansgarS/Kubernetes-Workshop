package de.ansgarsachs.scenarios.utils;

/**
 * Patient model used in steps for validation
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
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
