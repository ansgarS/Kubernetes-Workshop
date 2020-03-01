package de.ansgarsachs.patientservice.control;

import de.ansgarsachs.patientservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * PatientRegistry
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 01.03.20
 */
@Repository
public interface PatientRegistry extends JpaRepository<Patient, String>, JpaSpecificationExecutor<Patient> {
}
