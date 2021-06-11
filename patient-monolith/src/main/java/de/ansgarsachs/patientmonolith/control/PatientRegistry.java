package de.ansgarsachs.patientmonolith.control;

import de.ansgarsachs.exceptions.PatientNotFoundException;
import de.ansgarsachs.patientmonolith.entity.Patient;
import de.ansgarsachs.patientmonolith.entity.PatientDispenses;
import de.ansgarsachs.patientmonolith.entity.PatientIds;
import de.ansgarsachs.patientmonolith.entity.Prescription;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 * PatientRepository
 *
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
public class PatientRegistry {
    public List<Patient> patients = new ArrayList<>();
    private List<Prescription> prescriptions = new ArrayList<>();

    public boolean hasPatient(String id) {
        return patients.stream().filter(patient -> patient.getId().equals(id)).toArray().length == 1;
    }

    public String addPatient(Patient patient) {
        patient.setId(UUID.randomUUID().toString());
        patients.add(patient);

        return patient.getId();
    }

    public PatientIds getAllPatientIds() {
        PatientIds patientIds = new PatientIds();
        patientIds.setPatients(
                patients.stream().map(Patient::getId).collect(Collectors.toList())
        );
        return patientIds;
    }

    public String addPrescription(Prescription prescription) {
        if (hasPatient(prescription.getPatientId())) {
            prescription.setId(UUID.randomUUID().toString());
            prescriptions.add(prescription);
            return prescription.getId();
        } else {
            throw new PatientNotFoundException("Could not find patient");
        }
    }

    public PatientDispenses getCosts(String patientId) {
        PatientDispenses dispenses = new PatientDispenses();

        dispenses.setPatientId(patientId);
        dispenses.setCosts(
                prescriptions.stream()
                        .filter(prescription -> prescription.getPatientId().equals(patientId))
                        .map(Prescription::getPrice)
                        .reduce(0.0, Double::sum)
        );

        return dispenses;
    }
}
