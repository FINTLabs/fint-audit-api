package no.fint.audit

import no.fint.event.model.Event
import spock.lang.Specification

class FintAuditServiceSpec extends Specification {

    private TestableFintAuditService fintAuditService
    private Event event

    void setup() {
        fintAuditService = new TestableFintAuditService()
        event = new Event()
    }

    void cleanup() {
        FintAudit.reset()
    }

    def "Audit event"() {
        when:
        fintAuditService.audit(event)

        then:
        fintAuditService.receivedEvent == event
    }

    def "Audit trace event given AuditState.trace set to true"() {
        given:
        FintAudit.trace = true

        when:
        fintAuditService.trace(event)

        then:
        fintAuditService.receivedEvent == event
    }

    def "Do not audit trace event given AuditState.trace set to false"() {
        given:
        FintAudit.trace = false

        when:
        fintAuditService.trace(event)

        then:
        !fintAuditService.receivedEvent
    }

}
