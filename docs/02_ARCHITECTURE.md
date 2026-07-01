# Livio - Architecture Documentation

**Version:** M0 Foundation  
**Status:** Architecture baseline / No runtime implementation

---

## 1. Clean Multi-Module MVI Architecture Overview

Livio targets Clean Architecture with a multi-module Android project structure and MVI presentation pattern.

The architecture separates application wiring, feature capabilities, shared core contracts, domain logic, data access, and UI state handling.

```text
+-----------------------------------------+
|               app                       |
| Application entry point and app wiring  |
+-----------------------------------------+
                    |
                    v
+-----------------------------------------+
|              feature/*                  |
| User-facing capability modules          |
+-----------------------------------------+
                    |
                    v
+-----------------------------------------+
|               core/*                    |
| Shared contracts, models, services, UI  |
+-----------------------------------------+
```

**Dependency Direction:**

```text
:app
  -> :feature:*
  -> :core:*

:feature:*
  -> :core:*

:core:*
  -> approved lower-level :core:* modules only
```

M0 registers module boundaries only. Later milestones add Clean Architecture contracts, MVI state contracts, implementations, tests, navigation, and UI in small capability PRs.

---

## 2. Clean Architecture Layer Model

Planned runtime layering:

```text
Presentation
  -> Domain
  -> Data
  -> Network / Database / DataStore
```

**Layer Ownership:**

- **Presentation:** UI state, screens, UI events, feature routes, and ViewModels.
- **Domain:** Repository interfaces, use cases, result contracts, error contracts, and domain models.
- **Data:** Repository implementations, mappers, cache coordination, and data-source orchestration.
- **Network:** API service contracts, DTO boundaries, HTTP behavior, and endpoint traceability.
- **Database:** Room entities, DAOs, schema export, migrations, and local query behavior.
- **DataStore:** Preferences, settings persistence, and lightweight local state.

---

## 3. MVI Pattern

Livio targets MVI for feature presentation state.

**Target Data Flow:**

```text
User Action -> UI Event -> ViewModel -> Use Case -> Repository Contract
Repository Result -> ViewModel State Reducer -> UI State -> Screen
```

**MVI Responsibilities:**

- UI emits events; UI does not call repositories directly.
- ViewModels translate UI events into use case calls.
- Reducers produce immutable UI state.
- Screens render state and send events upward.
- One feature should not read another feature module state directly.

M0 does not add ViewModels, UI state classes, events, reducers, or screens.

---

## 4. Key Architectural Principles

### 4.1 Directional Dependencies

- App may depend on feature and core modules.
- Feature modules may depend on approved core modules.
- Feature modules must not depend on other feature modules.
- Domain contracts must not depend on Android framework types.

### 4.2 Single Source of Truth

- Repository implementations own data coordination.
- Local persistence owns offline state when M3 adds Room and DataStore.
- ViewModels expose screen state from domain results and repository streams.
- Screens do not duplicate repository or persistence state.

### 4.3 Unidirectional Data Flow

- State flows down from ViewModel to screen.
- Events flow up from screen to ViewModel.
- Domain and data layers do not depend on presentation state.

### 4.4 Separation of Concerns

- Presentation owns UI behavior only.
- Domain owns business contracts and use case orchestration.
- Data owns repository implementations and source selection.
- Network, database, and datastore modules own their platform-specific details.

### 4.5 Dependency Inversion

- Domain defines repository contracts.
- Data implements repository contracts.
- Presentation depends on use cases and domain contracts, not concrete data sources.

---

## 5. Layer Responsibilities

### 5.1 Presentation Layer

**What it does:**

- Renders Compose UI.
- Handles user interactions.
- Observes ViewModel state.
- Sends UI events to ViewModels.
- Requests navigation through approved route contracts.

**What it does not do:**

- Call Retrofit, Room, or DataStore directly.
- Own business rules.
- Parse DTOs.
- Expose Room entities to UI.

**Planned ownership:** M4, M5, and M6-M10.

### 5.2 Domain Layer

**What it does:**

- Defines repository interfaces.
- Defines use case contracts.
- Defines domain models.
- Defines result and error contracts.

**What it does not do:**

- Depend on Android framework APIs.
- Depend on Retrofit DTOs.
- Depend on Room entities.
- Own UI copy or Compose state.

**Planned ownership:** M1.

### 5.3 Data Layer

**What it does:**

- Implements repository interfaces.
- Coordinates network, database, and datastore sources.
- Maps DTOs and entities into domain models.
- Owns cache and offline behavior.

**What it does not do:**

- Render UI.
- Define feature routes.
- Leak DTOs or entities into domain or presentation.

**Planned ownership:** M3.

### 5.4 Network Layer

**What it does:**

- Defines DummyJSON API service boundaries.
- Owns DTO types.
- Owns serialization behavior.
- Supports endpoint traceability.

**What it does not do:**

- Expose DTOs to UI.
- Persist Room entities.
- Log tokens, secrets, or PII.

**Planned ownership:** M2.

### 5.5 Database and DataStore Layers

**What they do:**

- Database owns Room schema, entities, DAOs, migrations, and query behavior.
- DataStore owns preferences, settings state, and lightweight local persistence.

**What they do not do:**

- Define UI state.
- Define API DTOs.
- Bypass repository contracts.

**Planned ownership:** M3.

---

## 6. Dependency Injection

Livio targets Hilt for runtime dependency graph wiring.

**Planned DI Ownership:**

```text
app
  -> application-level setup

core/network
  -> network clients and API services

core/data
  -> repository bindings

core/database
  -> Room database, DAOs, and migrations

core/datastore
  -> DataStore instances and preference services

feature/*
  -> feature-specific ViewModel dependencies
```

**DI Rules:**

- Hilt modules are not added in M0.
- Bind interfaces to implementations in the owning data or platform module.
- Keep feature modules from binding shared app-wide dependencies directly.
- Keep secrets and environment-specific values out of committed source files.

---

## 7. State Management

### 7.1 Target ViewModel State Pattern

- ViewModels expose immutable UI state.
- UI state models represent loading, content, empty, and error states.
- UI events are explicit and scoped to the owning feature.
- Long-running work runs through use cases and repository contracts.

### 7.2 Screen State Collection

- Screens observe ViewModel state.
- Screens render state and emit events.
- Screens do not mutate repository state directly.
- Screens do not hold business decisions in local Compose state.

M0 does not add ViewModel, state, event, or screen classes.

---

## 8. Error Handling

**Target Pattern:**

```text
Data source error
  -> Data mapper
  -> Domain error contract
  -> Use case result
  -> ViewModel state
  -> Screen error UI
```

**Rules:**

- Data-source exceptions must be mapped before crossing into domain.
- Domain errors should be typed and testable.
- UI should receive display-ready state, not raw exceptions.
- Network, database, and serialization errors should remain observable in diagnostics without leaking sensitive data.

M1 owns result and error contracts. M2 and M3 add concrete mapping behavior.

---

## 9. DummyJSON Integration

DummyJSON is the planned sample API source for Livio.

**Endpoint Families:**

- auth
- users
- products
- carts
- recipes
- posts
- comments
- todos
- quotes

**Integration Rules:**

- API services and DTOs belong to M2.
- Repository implementations belong to M3.
- DummyJSON user data must be mapped into safe domain models before UI use.
- Only whitelisted user fields may cross into feature UI.
- Full endpoint traceability belongs to `LIVIO-M2-001`.

---

## 10. Testing Strategy

M0 validation is build and tooling validation only.

**Future Test Ownership:**

- **M1:** Contract tests for domain models, result types, use cases, and repository interfaces.
- **M2:** API contract tests, DTO mapper tests, and endpoint traceability validation.
- **M3:** Repository tests, Room tests, DataStore tests, sync tests, and migration tests.
- **M4:** Design system component validation and accessibility preview coverage.
- **M5:** Navigation route tests, guarded route tests, and deep-link validation.
- **M6-M10:** Feature workflow tests.
- **M11:** Hardening tests for quality, accessibility, privacy, security, and performance.
- **M12:** Release candidate evidence and portfolio artifacts.

---

## 11. Performance Considerations

**Planned Performance Rules:**

- Use coroutines for asynchronous work.
- Keep blocking I/O off the main thread.
- Use paging or bounded loading for large lists.
- Use Coil for image loading and caching.
- Use Room and DataStore through repository boundaries.
- Keep expensive mapping out of composable rendering paths.

M0 does not implement runtime performance behavior. Performance validation starts when runtime code is added.

---

## 12. Security and Privacy Architecture

**Baseline Rules:**

- Do not log tokens, secrets, or PII.
- Do not commit local secrets.
- Do not deep-link secrets.
- Do not expose DTOs with unsafe user fields to UI.
- Keep DummyJSON user data behind safe domain-model whitelisting.
- Optional TOTP work is defer-by-default.
- If TOTP is implemented later, secrets must use POST request bodies and must not be logged or deep-linked.

**Planned Security Ownership:**

- M2 owns network safety and endpoint traceability.
- M3 owns persistence safety.
- M11 owns hardening, privacy checks, and security review.

---

## 13. Scalability and Maintainability

**Current Architecture Supports:**

- Small capability PRs.
- Independent feature module development.
- Shared core contracts.
- Explicit ownership for data, domain, presentation, navigation, and DI.
- Gradle validation on every PR.

**Future Enhancements:**

- Offline-first repository behavior.
- Sync and backup boundaries.
- Feature flags.
- Diagnostics and observability.
- Release candidate evidence and portfolio documentation.

---

## 14. Current M0 State

**M0 Includes:**

- Module registration.
- Convention plugins.
- Quality checks.
- Package documentation placeholders.
- Module README files.
- Baseline architecture documentation.

**M0 Excludes:**

- Public runtime APIs.
- Repository interfaces.
- Repository implementations.
- DTOs.
- Entities.
- DAOs.
- Screens.
- ViewModels.
- Feature routes.
- Hilt modules.

---

## 15. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file describes the architecture target without claiming implementation outside M0.
