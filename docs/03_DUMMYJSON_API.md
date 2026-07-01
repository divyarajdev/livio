# Livio - DummyJSON API Documentation

**Version:** M0 Foundation  
**Status:** Placeholder / Full endpoint matrix owned by M2

---

## 1. Purpose

This document reserves the API documentation location for DummyJSON integration.

M0 does not define final API services, DTOs, repository methods, or route-family contracts. Full
endpoint traceability belongs to M2.

---

## 2. Current M0 State

M0 includes:

- `:core:network` module boundary
- approved version catalog dependencies for Retrofit, OkHttp, Kotlinx Serialization, and
  MockWebServer
- API documentation placeholder

M0 excludes:

- Retrofit service interfaces
- DTO classes
- request and response mappers
- authentication implementation
- endpoint matrix completion
- MockWebServer tests
- custom response tooling

---

## 3. Endpoint Families Planned For M2

M2 will document and validate the supported DummyJSON endpoint families:

- auth
- users
- products
- carts
- recipes
- posts
- comments
- todos
- quotes

M2 must include coverage notes for:

- canonical auth routes
- users filter behavior
- todos by user
- supported random behavior
- pagination and limit/skip behavior where supported
- endpoint ownership by repository and use case

---

## 4. API Boundary Rules

- DTOs stay inside network/data boundaries.
- UI must consume domain-safe models, not DTOs.
- Repository contracts must not expose Retrofit, OkHttp, or serialization-specific types.
- HTTP errors must map to domain-safe error contracts.
- Endpoint ownership must be traceable before feature implementation depends on it.

---

## 5. DummyJSON User PII Handling

DummyJSON user responses can contain fields that should not be forwarded blindly to UI or logs.

M2 must define:

- field whitelist for user domain models
- redaction rules for logs
- mapping rules for sensitive or irrelevant fields
- test coverage for mapper redaction
- reviewer checklist for PII-safe model exposure

No token, password, secret, or full user payload should be logged.

---

## 6. HTTP Mock Scope

Phase 1 supports HTTP mock testing through approved test tooling.

Custom Response is out of scope for Phase 1 unless a later approved task explicitly adds it.

---

## 7. Planned Owning Milestone

- M2 owns API service contracts, DTOs, endpoint matrix, API tests, and mapper tests.
- M3 owns repository implementations that consume the API layer.
- M6-M10 consume domain-safe results from completed repository contracts.

---

## 8. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file remains a placeholder until M2 API work starts.
