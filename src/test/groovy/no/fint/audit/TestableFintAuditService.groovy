package no.fint.audit

import no.fint.event.model.Event
import no.fint.event.model.Status

class TestableFintAuditService implements FintAuditService {
    Event receivedEvent

    @Override
    void audit(Event event, Status... statuses) {
        receivedEvent = event
    }

    @Override
    void audit(Event event, boolean clearData) {
        receivedEvent = event
    }
}
