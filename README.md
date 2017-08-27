# FINT Audit API

[![Build Status](https://travis-ci.org/FINTlibs/fint-audit-api.svg?branch=master)](https://travis-ci.org/FINTlibs/fint-audit-api)

Interface for auditing

## Installation

build.gradle

```groovy
repositories {
    maven {
        url  "http://dl.bintray.com/fint/maven"
    }
}

compile('no.fint:fint-audit-api:1.1.1')
```

## Usage

Add `@EnableFintAudit` to the main application class.

Autowire in FintAuditService:
```java
@Autowired
private FintAuditService fintAuditService;
```

Audit event and clear data:
```java
fintAuditService.audit(event);
```

Set status on event and audit event. If there are multiple statuses, this will cause multiple audit log statements.  
In the example below it will first audit log with the Status `CACHE_RESPONSE` and then `SENT_TO_CLIENT`.
After the audit statement the event will have the status `SENT_TO_CLIENT`.
```java
fintAuditService.audit(event, Status.CACHE_RESPONSE, Status.SENT_TO_CLIENT)
```

Audit event and send in a flag to indicate if the data should be cleared:
```java
fintAuditService.audit(event, false);
```
