package no.fint.audit;

import no.fint.events.model.Event;

import java.util.List;

public interface AuditAdminInterface {
    public List<Event> getEventsByCorrId(String corrId);
    public List<Event> getEventBySource(String source);
    public List<Event> getAllEvents();
    public List<Event> searchEvents(String searchString);
}
