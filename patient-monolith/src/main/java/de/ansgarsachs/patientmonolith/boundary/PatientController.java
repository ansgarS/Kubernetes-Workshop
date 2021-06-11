package de.ansgarsachs.patientmonolith.boundary;

import de.ansgarsachs.patientmonolith.control.PatientRegistry;
import de.ansgarsachs.patientmonolith.entity.Patient;
import de.ansgarsachs.patientmonolith.entity.PatientDispenses;
import de.ansgarsachs.patientmonolith.entity.EntityId;
import de.ansgarsachs.patientmonolith.entity.PatientIds;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * PatientController
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 23.02.20
 */
@RestController
@RequestMapping(value = "/patients")
public class PatientController {
    Logger log = LoggerFactory.getLogger(PatientController.class);

    @Resource(name = "patientregistry")
    private PatientRegistry repository;

    @PostMapping(produces = {"application/json"})
    public ResponseEntity<EntityId> addPatient(@RequestBody Patient patient) {
        String id = repository.addPatient(patient);

        EntityId entityId = new EntityId();
        entityId.setId(id);

        return new ResponseEntity<>(entityId, HttpStatus.CREATED);
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<PatientIds> getPatientIds() {
        return new ResponseEntity<PatientIds>(repository.getAllPatientIds(), HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}/costs", produces = {"application/json"})
    public ResponseEntity<PatientDispenses> getPatientDispenses(@PathVariable String patientId) {
        try {
            return new ResponseEntity<>(repository.getCosts(patientId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
