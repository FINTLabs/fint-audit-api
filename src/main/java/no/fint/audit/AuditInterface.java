package no.fint.audit;


import no.fint.events.model.Event;

public interface AuditInterface {
    public void audit(Event auditEvent);
}
