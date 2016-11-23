package no.fint.audit.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import no.fint.event.model.Event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
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

    public AuditEvent(Event e) {
        corrId = e.getCorrId();
        source = e.getSource();
        orgId = e.getOrgId();
        clearData = true;
        event = prepareEvent(e);
        timestamp = System.currentTimeMillis();
    }

    public AuditEvent(Event e, boolean clearData) {
        corrId = e.getCorrId();
        source = e.getSource();
        orgId = e.getOrgId();
        this.clearData = clearData;
        event = prepareEvent(e);
        timestamp = System.currentTimeMillis();
    }

    private Event prepareEvent(Event e) {
        if (clearData) {
            event = new Event();
            event.setCorrId(e.getCorrId());
            event.setAction(e.getAction());
            event.setStatus(e.getStatus());
            event.setTime(e.getTime());
            event.setOrgId(e.getOrgId());
            event.setSource(e.getSource());
            event.setClient(e.getClient());
            event.setMessage(e.getMessage());
            event.setData(CLEARED_DATA);
            return event;
        } else {
            return e;
        }
    }


}