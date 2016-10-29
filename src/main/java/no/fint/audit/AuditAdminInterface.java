package no.fint.audit;

import no.fint.audit.model.AuditEvent;

import java.util.List;

public interface AuditAdminInterface {
    public List<AuditEvent> getEventsByCorrId(String corrId);
    public List<AuditEvent> getAllEvents();
    public List<AuditEvent> searchEvents(String searchString);
}
