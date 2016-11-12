package no.fint.audit;


import no.fint.event.Event;

public interface AuditInterface {
    public void audit(Event event, boolean clearData);
}
