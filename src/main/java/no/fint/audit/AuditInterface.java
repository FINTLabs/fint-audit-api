package no.fint.audit;


import no.fint.audit.model.AuditEvent;

public interface AuditInterface {
    public void audit(AuditEvent auditEvent);
}
