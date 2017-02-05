# FINT Audit API

[![Build Status](https://travis-ci.org/FINTlibs/fint-audit-api.svg?branch=master)](https://travis-ci.org/FINTlibs/fint-audit-api)

Interface for auditing

## Installation

build.gradle

```
repositories {
    maven {
        url  "http://dl.bintray.com/fint/maven"
    }
}

compile('no.fint:fint-audit-api:0.0.19')
```

## Usage

The plugin should create a Spring `@Configuration` class (`no.fint.audit.FintAuditConfig`) that is responsible to create all the necessary beans.


## Upload

Upload release to bintray

`./gradlew bintrayUpload -PbintrayUser=<username> -PbintrayKey=<apiKey>`