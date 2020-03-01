package de.ansgarsachs.patientservice.boundary;

import de.ansgarsachs.patientservice.control.PatientRegistry;
import de.ansgarsachs.patientservice.entity.EntityId;
import de.ansgarsachs.patientservice.entity.Patient;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * PatientController
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 01.03.20
 */
@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    private PatientRegistry patientRegistry;

    public PatientController(PatientRegistry patientRegistry) {
        this.patientRegistry = patientRegistry;
    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<EntityId> addPatient(@RequestBody Patient patient) {
        String id = UUID.randomUUID().toString();

        patient.setId(id);
        patientRegistry.save(patient);

        EntityId entityId = new EntityId();
        entityId.setId(id);

        return new ResponseEntity<>(entityId, HttpStatus.CREATED);
    }
}
