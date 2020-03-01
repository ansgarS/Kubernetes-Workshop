package de.ansgarsachs.patientmonolith.boundary;

import de.ansgarsachs.exceptions.PatientNotFoundException;
import de.ansgarsachs.patientmonolith.control.PatientRegistry;
import de.ansgarsachs.patientmonolith.entity.EntityId;
import de.ansgarsachs.patientmonolith.entity.Prescription;
import javax.annotation.Resource;
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
 * @since 23.02.20
 */
@RestController
@RequestMapping(value = "/prescriptions")
public class PrescriptionController {
    @Resource(name = "patientregistry")
    private PatientRegistry repository;

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<EntityId> addPrescription(@RequestBody Prescription prescription) {
        try {
            String id = repository.addPrescription(prescription);

            EntityId prescriptionId = new EntityId();
            prescriptionId.setId(id);

            return new ResponseEntity<>(prescriptionId, HttpStatus.CREATED);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
