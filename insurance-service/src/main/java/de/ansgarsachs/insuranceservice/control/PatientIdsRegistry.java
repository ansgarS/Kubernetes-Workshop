package de.ansgarsachs.insuranceservice.control;

import de.ansgarsachs.insuranceservice.entity.PatientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * PatientIdsRegistry
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 01.03.20
 */
public interface PatientIdsRegistry extends JpaRepository<PatientId, String>, JpaSpecificationExecutor<PatientId> {
}
