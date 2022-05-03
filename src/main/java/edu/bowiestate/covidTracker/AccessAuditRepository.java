package edu.bowiestate.covidTracker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessAuditRepository extends JpaRepository<AccessAudit, Long> {

}
