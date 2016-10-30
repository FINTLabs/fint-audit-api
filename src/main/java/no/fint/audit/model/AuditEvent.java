package no.fint.audit.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuditEvent implements Serializable {
    private String corrId;
    private String source;
    // TODO: 29.10.2016 Need to change the datatype from Object to FINT event object when it is finished 
    private Object event;
    
}
