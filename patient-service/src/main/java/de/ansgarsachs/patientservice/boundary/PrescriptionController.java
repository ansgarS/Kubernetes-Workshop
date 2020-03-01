package de.ansgarsachs.patientservice.boundary;

import de.ansgarsachs.patientservice.control.PatientRegistry;
import de.ansgarsachs.patientservice.control.PrescriptionRegistry;
import de.ansgarsachs.patientservice.entity.EntityId;
import de.ansgarsachs.patientservice.entity.Prescription;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PrescriptionController
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 01.03.20
 */
@RestController
@RequestMapping(value = "/prescriptions")
public class PrescriptionController {
    private PatientRegistry patientRegistry;
    private PrescriptionRegistry prescriptionRegistry;

    public PrescriptionController(PatientRegistry patientRegistry, PrescriptionRegistry prescriptionRegistry) {
        this.patientRegistry = patientRegistry;
        this.prescriptionRegistry = prescriptionRegistry;
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<EntityId> addPrescription(@RequestBody Prescription prescription) {
        if (!patientRegistry.existsById(prescription.getPatientId())) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        String id = UUID.randomUUID().toString();
        prescription.setId(id);
        prescriptionRegistry.save(prescription);

        EntityId prescriptionId = new EntityId();
        prescriptionId.setId(id);

        return new ResponseEntity<>(prescriptionId, HttpStatus.CREATED);
    }
}
