package no.fint.audit.model;

import lombok.Data;
import no.fint.event.model.Event;
import no.fint.event.model.EventRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class AuditEvent implements Serializable {
    private String corrId;
    private String source;
    private String orgId;
    private long timestamp;
    private Event event;
    private boolean clearData;

    private static final List<String> CLEARED_DATA = new ArrayList<>();
    static final String DATA_CLEARED_MESSAGE = "Data field cleared by audit service.";

    static {
        CLEARED_DATA.add(DATA_CLEARED_MESSAGE);
    }

    public AuditEvent() {
        timestamp = System.currentTimeMillis();
        clearData = true;
    }

    public AuditEvent(Event e) {
        this(e, true);
    }

    public AuditEvent(Event e, boolean clearData) {
        corrId = e.getCorrId();
        source = e.getSource();
        orgId = e.getOrgId();
        this.clearData = clearData;
        event = prepareEvent(e);
        timestamp = System.currentTimeMillis();
    }

    @SuppressWarnings("unchecked")
    private Event prepareEvent(Event e) {
        if (clearData) {
            Event e2 = new Event(e);
            e2.setData(CLEARED_DATA);
            return e2;
        } else {
            return e;
        }
    }

    public void filterOdataQuery() {
        EventRequest request = event.getRequest();
        if (request != null && request.getQuery() != null) {
            request.setQuery(request.getFilteredQuery());
        }
    }

}