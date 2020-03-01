package de.ansgarsachs.patientservice.control;

import de.ansgarsachs.patientservice.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * PrescriptionRegistry
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 01.03.20
 */
@Repository
public interface PrescriptionRegistry extends JpaRepository<Prescription, String>, JpaSpecificationExecutor<Prescription> {
}
