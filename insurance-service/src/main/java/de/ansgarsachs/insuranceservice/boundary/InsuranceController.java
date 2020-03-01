package de.ansgarsachs.insuranceservice.boundary;

import de.ansgarsachs.insuranceservice.control.PatientDispensesRegistry;
import de.ansgarsachs.insuranceservice.control.PatientIdsRegistry;
import de.ansgarsachs.insuranceservice.entity.PatientDispenses;
import de.ansgarsachs.insuranceservice.entity.PatientId;
import de.ansgarsachs.insuranceservice.entity.PatientIds;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * InsuranceController
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 27.02.20
 */
@RestController
@RequestMapping(value = "/patients")
public class InsuranceController {
    Logger log = LoggerFactory.getLogger(InsuranceController.class);

    private PatientDispensesRegistry patientDispensesRegistry;
    private PatientIdsRegistry patientIdsRegistry;

    public InsuranceController(PatientIdsRegistry patientIdsRegistry, PatientDispensesRegistry patientDispensesRegistry) {
        this.patientDispensesRegistry = patientDispensesRegistry;
        this.patientIdsRegistry = patientIdsRegistry;
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<PatientIds> getPatientIds() {
        PatientIds patientIds = new PatientIds();

        patientIds.setPatients(
                patientIdsRegistry
                        .findAll()
                        .stream()
                        .map(PatientId::getId)
                        .collect(Collectors.toList())
        );

        return new ResponseEntity<>(patientIds, HttpStatus.OK);
    }

    @GetMapping(value = "/{patientId}/costs", produces = {"application/json"})
    public ResponseEntity<PatientDispenses> getPatientDispenses(@PathVariable String patientId) {
        try {
            return new ResponseEntity<>(patientDispensesRegistry.getOne(patientId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
