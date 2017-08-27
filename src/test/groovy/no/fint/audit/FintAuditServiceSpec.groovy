package no.fint.audit

import no.fint.event.model.Event
import no.fint.event.model.Status
import spock.lang.Specification

class FintAuditServiceSpec extends Specification {
    private FintAuditService fintAuditService
    private List<Event> events

    void setup() {
        events = []
        fintAuditService = new FintAuditService() {
            @Override
            void audit(Event event, boolean clearData) {
                events.add(event)
            }
        }
    }

    def "Audit event and set status"() {
        given:
        def event = new Event(status: Status.NEW)

        when:
        fintAuditService.audit(event, Status.CACHE_RESPONSE)

        then:
        events.size() == 1
        events[0].status == Status.CACHE_RESPONSE
        event.status == Status.CACHE_RESPONSE
    }

    def "Audit multiple events with different statuses"() {
        given:
        def event = new Event(status: Status.NEW)

        when:
        fintAuditService.audit(event, Status.CACHE, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT)

        then:
        events.size() == 3
        events[0].status == Status.CACHE
        events[1].status == Status.CACHE_RESPONSE
        events[2].status == Status.SENT_TO_CLIENT
        event.status == Status.SENT_TO_CLIENT
    }
}
