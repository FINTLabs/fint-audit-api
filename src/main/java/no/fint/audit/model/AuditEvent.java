package no.fint.audit.model;

import lombok.Data;
import no.fint.event.Event;

import java.io.Serializable;

@Data
public class AuditEvent implements Serializable {
    private String corrId;
    private String source;
    private Event event;
    
}
