package no.fint.audit;


import no.fint.event.model.Event;

public interface FintAuditService {
    default void audit(Event event) {
        audit(event, true);
    }

    void audit(Event event, boolean clearData);
}
