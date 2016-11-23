# FINT Audit API

[![Build Status](https://jenkins.rogfk.no/buildStatus/icon?job=FINTprosjektet/fint-audit-api/master)](https://jenkins.rogfk.no/job/FINTprosjektet/job/fint-audit-api/job/master/)

Interface for auditing

## Installation

build.gradle

```
repositories {
    maven {
        url  "http://dl.bintray.com/fint/maven"
    }
}

compile('no.fint:fint-audit-api:0.0.17')
```

## Usage

The plugin should create a Spring `@Configuration` class (`no.fint.audit.FintAuditConfig`) that is responsible to create all the necessary beans.


## Upload

Upload release to bintray

`./gradlew bintrayUpload -PbintrayUser=<username> -PbintrayKey=<apiKey>`