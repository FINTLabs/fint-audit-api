package no.fint.audit;


import no.fint.event.model.Event;

public interface FintAuditService {
    public void audit(Event event, boolean clearData);
}
