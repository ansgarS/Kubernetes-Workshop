package de.ansgarsachs.patientmonolith.control;

import de.ansgarsachs.patientmonolith.entity.Patient;
import de.ansgarsachs.patientmonolith.entity.PatientIds;
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
@Service
public class PatientRepository {
    public List<Patient> patients = new ArrayList<>();

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
}
