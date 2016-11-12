package no.fint.audit;


import no.fint.event.model.Event;

public interface AuditInterface {
    public void audit(Event event, boolean clearData);
}
