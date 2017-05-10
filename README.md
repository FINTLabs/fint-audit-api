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

compile('no.fint:fint-audit-api:0.0.21')
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

Audit event and send in a flag to indicate if the data should be cleared:
```java
fintAuditService.audit(event, false);
```