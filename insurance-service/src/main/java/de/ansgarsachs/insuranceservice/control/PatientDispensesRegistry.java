package de.ansgarsachs.insuranceservice.control;

import de.ansgarsachs.insuranceservice.entity.PatientDispenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * PatientRegistry
 *
 * @author Ansgar Sachs &lt;ansgar.sachs@cgm.com&gt;
 * @since 27.02.20
 */
@Repository
public interface PatientDispensesRegistry extends JpaRepository<PatientDispenses, String>, JpaSpecificationExecutor<PatientDispenses> {}
