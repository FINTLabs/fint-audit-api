package no.fint.audit;


import no.fint.event.model.Event;
import no.fint.event.model.Status;

public interface FintAuditService {
    default void audit(Event event) {
        audit(event, true);
    }

    default void audit(Event event, Status... statuses) {
        for (Status status : statuses) {
            Event tempEvent = new Event(event);
            tempEvent.setStatus(status);
            audit(tempEvent, true);
        }
        event.setStatus(statuses[statuses.length - 1]);
    }

    void audit(Event event, boolean clearData);
}
