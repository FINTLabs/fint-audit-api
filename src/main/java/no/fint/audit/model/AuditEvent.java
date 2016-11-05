package no.fint.audit.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import no.fint.events.model.Event;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@ToString
@EqualsAndHashCode
public class AuditEvent implements Serializable {
    private String corrId;
    private String source;
    private String orgId;
    private long timestamp;
    private Event event;
    private boolean clearData;

    final private String DATA_CLEARED_MESSAGE = "Data field cleared by audit service.";

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

    public String getCorrId() {
        return corrId;
    }

    public void setCorrId(String corrId) {
        this.corrId = corrId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    private Event prepareEvent(Event event) {
        Event e = event;

        if (Boolean.valueOf(clearData)) {
            e.getData().clear();
            e.getData().add(DATA_CLEARED_MESSAGE);
        }

        return e;
    }


}