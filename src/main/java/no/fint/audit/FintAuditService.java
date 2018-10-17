package no.fint.audit;


import no.fint.event.model.Event;
import no.fint.event.model.Status;

public interface FintAuditService {
    default void audit(Event event) {
        audit(event, true);
    }

    default void trace(Event event) {
        if (FintAudit.trace) {
            audit(event, true);
        }
    }

    void audit(Event event, Status... statuses);

    void audit(Event event, boolean clearData);
}
