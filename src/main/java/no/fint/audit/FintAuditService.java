package no.fint.audit;


import no.fint.event.model.Event;

public interface FintAuditService {
    void audit(Event event);

    void auditWithEventData(Event event);
}
