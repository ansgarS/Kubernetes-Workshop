package de.ansgarsachs.scenarios.steps;

import cucumber.runtime.java.guice.ScenarioScoped;
import de.ansgarsachs.scenarios.utils.Patient;
import de.ansgarsachs.scenarios.utils.Prescription;
import java.util.ArrayList;
import java.util.List;

/**
 * This World is the context of each scenario
 *
 * <p>
 *     Use this class to transfer data from one
 *     step to another.
 *     Keep in mind, that data is lost between
 *     scenarios.
 * </p>
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 13.11.19
 */
@ScenarioScoped
public class World {
    private List<Patient> patients;
    private Patient currentPatient;
    private List<Prescription> prescriptions;

    public World() {
        this.patients = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    void addPatient(Patient patient) {
        currentPatient = patient;

        patients.add(patient);
    }

    List<Patient> getAddedPatients() {
        return patients;
    }
    Patient getCurrentPatient() {
        return currentPatient;
    }
    List<Prescription> getAddedPrescriptions() { return  prescriptions; }
}
