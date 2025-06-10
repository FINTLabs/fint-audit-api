package no.fint.audit.model

import no.fint.event.model.Event
import no.fint.event.model.EventRequest
import spock.lang.Specification


class AuditEventSpec extends Specification {

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
        auditEvent.getEvent().getData().hashCode() != event.getData().hashCode()
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
        auditEvent.getEvent().getData().hashCode() == event.getData().hashCode()
    }

    def "filterOdataQuery does nothing when request is null"() {
        given:
        Event event = new Event("org", "src", "GET", "kind")
        AuditEvent auditEvent = new AuditEvent(event)

        when:
        auditEvent.filterOdataQuery()

        then:
        auditEvent.getEvent().getRequest() == null
    }

    def "filterOdataQuery does nothing when query is null"() {
        given:
        Event event = new Event("org", "src", "GET", "kind")
        EventRequest request = new EventRequest()
        event.request = request
        AuditEvent auditEvent = new AuditEvent(event, false)

        when:
        auditEvent.filterOdataQuery()

        then:
        auditEvent.getEvent().getRequest().getQuery() == null
    }

    def "filterOdataQuery masks a simple \$filter expression"() {
        given:
        String originalQuery = "? \$filter=name eq 'Test' &\$top=10".replace(" ", "")
        String expectedMasked = "?\$filter=***&\$top=10"
        Event event = new Event("org", "src", "GET", "kind")
        EventRequest request = new EventRequest()
        request.query = originalQuery
        event.request = request
        AuditEvent auditEvent = new AuditEvent(event, false)

        when:
        auditEvent.filterOdataQuery()
        String actual = auditEvent.getEvent().getRequest().getQuery()

        then:
        actual == expectedMasked
    }

    def "filterOdataQuery masks multiple filter occurrences ignoring case"() {
        given:
        String originalQuery = "\$Filter=foo eq 'bar'&\$FILTER=baz eq 'qux'"
        String expectedMasked = "\$Filter=***&\$FILTER=***"
        Event event = new Event("org", "src", "GET", "kind")
        EventRequest request = new EventRequest()
        request.query = originalQuery
        event.request = request
        AuditEvent auditEvent = new AuditEvent(event, false)

        when:
        auditEvent.filterOdataQuery()
        String actual = auditEvent.getEvent().getRequest().getQuery()

        then:
        actual == expectedMasked
    }

    def "filterOdataQuery masks message with \$filter expression"() {
        given:
        String originalMessage = "No case found for query: \$filter=tittel eq 'bob'"
        String expectedMasked = "No case found for query: \$filter=***"
        Event event = new Event("org", "src", "GET", "kind")
        event.setMessage(originalMessage)
        AuditEvent auditEvent = new AuditEvent(event, false)

        when:
        auditEvent.filterOdataQuery()
        String actual = auditEvent.getEvent().getMessage()

        then:
        actual == expectedMasked
    }

}
