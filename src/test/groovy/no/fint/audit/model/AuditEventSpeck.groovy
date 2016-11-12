package no.fint.audit.model

import no.fint.event.model.Event
import spock.lang.Specification


class AuditEventSpeck extends Specification {

    def "Create AuditEvent when clearing data field set to true"() {
        given:
        Event event = new Event("rogfk.no", "Felleskomponent", "GET", "C");
        event.addData("Hello world!")
        event.addData("Hello world again!")

        when:
        AuditEvent auditEvent = new AuditEvent(event)

        then:
        auditEvent.getCorrId() != null
        auditEvent.getOrgId() == "rogfk.no"
        auditEvent.getSource() == "Felleskomponent"
        auditEvent.getEvent().getData().size() == 1
        auditEvent.getEvent().getData().get(0) == auditEvent.DATA_CLEARED_MESSAGE
    }

    def "Create AuditEvent when clearing data field set to false"() {
        given:
        Event event = new Event("rogfk.no", "Felleskomponent", "GET", "C");
        event.addData("Hello world!")
        event.addData("Hello world again!")

        when:
        AuditEvent auditEvent = new AuditEvent(event, false)

        then:
        auditEvent.getCorrId() != null
        auditEvent.getOrgId() == "rogfk.no"
        auditEvent.getSource() == "Felleskomponent"
        auditEvent.getEvent().getData().size() == 2
    }
}
