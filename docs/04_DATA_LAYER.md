# Livio - Data Layer Documentation

**Version:** M0 Foundation  
**Status:** Data-layer baseline / No data implementation

**Locations:**
- `core/data/`
- `core/network/`
- `core/database/`
- `core/datastore/`
- `core/sync/`
- `core/backup/`

---

## 1. Structure

Current M0 structure:

```text
core/
|-- data/                       # Repository implementation boundary
|-- network/                    # API service and DTO boundary
|-- database/                   # Room boundary
|-- datastore/                  # DataStore boundary
|-- sync/                       # Sync worker boundary
`-- backup/                     # Backup and restore boundary
```

M0 tracks module boundaries and package documentation only. Runtime folders such as `repository/`, `mapper/`, `source/`, `dto/`, `entity/`, `dao/`, `worker/`, and `migration/` are added by the owning capability PR.

---

## 2. Current M0 State

M0 includes:

- `:core:data`
- `:core:network`
- `:core:database`
- `:core:datastore`
- `:core:sync`
- `:core:backup`
- Module build files
- Module README files
- Package documentation placeholders

M0 does not add:

- Repository implementation classes
- Remote data sources
- Local data sources
- DTOs
- Room entities
- DAOs
- DataStore keys
- Sync workers
- Migrations
- Offline cache logic

---

## 3. Data Ownership

### 3.1 `:core:data`

**Purpose:** Repository implementation boundary.

**Planned Responsibilities:**
- Implement domain repository contracts.
- Coordinate network, database, datastore, sync, and backup sources.
- Map source-specific models into domain models.
- Own cache policy and offline behavior.

**Not Responsible For:**
- Defining domain contracts.
- Rendering UI.
- Owning route contracts.
- Exposing DTOs or Room entities to features.

### 3.2 `:core:network`

**Purpose:** API service, DTO, serialization, and HTTP boundary.

**Planned Responsibilities:**
- Define DummyJSON API service contracts.
- Define DTOs.
- Configure serialization behavior.
- Support endpoint traceability.
- Keep HTTP mock support in Phase 1 scope.

**Not Responsible For:**
- Repository implementation decisions.
- Room schema ownership.
- UI state ownership.
- Custom Response behavior, which remains out of scope for Phase 1.

### 3.3 `:core:database`

**Purpose:** Room persistence boundary.

**Planned Responsibilities:**
- Define Room entities.
- Define DAOs.
- Own schema export.
- Own migrations and rollback validation.

**Not Responsible For:**
- Exposing entities to UI.
- Calling API services directly from UI.
- Defining domain repository contracts.

### 3.4 `:core:datastore`

**Purpose:** DataStore persistence boundary.

**Planned Responsibilities:**
- Define preference keys.
- Persist lightweight user and app settings.
- Expose settings through repository-owned access patterns.

**Not Responsible For:**
- Becoming a public feature contract.
- Storing secrets without an approved security design.
- Replacing Room for structured relational data.

### 3.5 `:core:sync`

**Purpose:** Background sync boundary.

**Planned Responsibilities:**
- Own WorkManager-backed sync work.
- Coordinate retry policy after repository behavior exists.
- Support offline refresh and conflict-handling work owned by M3.

**Not Responsible For:**
- Defining feature UI state.
- Bypassing repository contracts.
- Starting sync before data contracts exist.

### 3.6 `:core:backup`

**Purpose:** Backup and restore boundary.

**Planned Responsibilities:**
- Define backup and restore contracts after persistence exists.
- Support M3 rollback validation and later hardening work.

**Not Responsible For:**
- Owning user-facing restore UI.
- Exporting sensitive data without approved privacy rules.

---

## 4. Planned Data Model Boundaries

### 4.1 Domain Models

**Owner:** `:core:model` and `:core:domain`  
**Milestone:** M1

Domain models are safe models used by repository contracts, use cases, ViewModels, and UI state.

### 4.2 Network DTOs

**Owner:** `:core:network`  
**Milestone:** M2

DTOs represent DummyJSON API payloads. DTOs must be mapped before crossing into domain or presentation.

### 4.3 Room Entities

**Owner:** `:core:database`  
**Milestone:** M3

Entities represent local database tables. Entities must not cross into UI or domain contracts directly.

### 4.4 DataStore Keys

**Owner:** `:core:datastore`  
**Milestone:** M3

DataStore keys represent local settings and lightweight app state. Keys must not become public feature contracts.

---

## 5. Planned Repository Implementations

Repository implementation classes are not added in M0.

Planned repository implementation work starts after M1 domain contracts and M2 API boundaries exist.

**Planned Repository Families:**
- Authentication and session data
- User profile data
- Product data
- Cart data
- Order data
- Recipe data
- Todo data
- Post data
- Comment data
- Quote data
- Saved item data
- Settings data
- Developer diagnostics data

**Repository Implementation Rules:**
- Implement only approved domain repository contracts.
- Return domain-safe models and typed errors.
- Hide DTOs, entities, DataStore keys, and source-specific exceptions.
- Keep UI copy out of repository implementations.
- Keep feature modules independent.

---

## 6. Data Transformation

### From API To UI

```text
DummyJSON response
  -> Network DTO
  -> Mapper
  -> Domain model
  -> Repository result
  -> Use case result
  -> ViewModel state
  -> Compose UI
```

### From Database To UI

```text
Room query
  -> Entity
  -> Mapper
  -> Domain model
  -> Repository stream
  -> Use case result
  -> ViewModel state
  -> Compose UI
```

### From UI To Persistence

```text
UI event
  -> ViewModel
  -> Use case
  -> Repository contract
  -> Data source
  -> Database or DataStore write
```

M0 does not implement these transformations. M2 owns API DTO mapping. M3 owns repository, database, and datastore mapping.

---

## 7. Error Handling

**Target Pattern:**

```text
Source failure
  -> Source-specific exception mapping
  -> Domain error contract
  -> Repository result
  -> Use case result
  -> ViewModel state
  -> UI error state
```

**Rules:**
- Network and database failures must map to domain-safe error contracts.
- Raw exceptions must not leak into UI state.
- Serialization failures must be observable in diagnostics without exposing payload-sensitive data.
- Repository implementations must support testable failure paths.

M1 owns result and error contracts. M2 and M3 own concrete source mappings.

---

## 8. Logging and Privacy

**Allowed:**
- Non-sensitive lifecycle events.
- Non-sensitive diagnostics.
- Redacted failure context.
- Build and validation output.

**Not Allowed:**
- Tokens.
- Secrets.
- TOTP secrets.
- Raw PII.
- Full DummyJSON user payloads.
- Request or response bodies containing sensitive fields.

DummyJSON user data must be mapped into safe domain models before UI use. Only whitelisted user fields may cross into feature UI.

---

## 9. Performance Optimizations

Planned performance rules:

- Use coroutines for asynchronous data work.
- Keep blocking I/O off the main thread.
- Use Room for structured local persistence.
- Use DataStore for lightweight settings.
- Use paging or bounded loading for large lists.
- Keep mapping work outside composable rendering paths.
- Use WorkManager for background sync work after sync contracts exist.

M0 does not implement runtime performance behavior. Performance validation starts when data implementation is added.

---

## 10. Planned Owning Milestones

- **M1:** Domain models, result contracts, repository interfaces, and use case contracts.
- **M2:** API service contracts, DTO boundaries, endpoint traceability, and mapper contracts.
- **M3:** Repository implementations, Room, DataStore, offline behavior, migrations, sync, backup, and rollback tests.
- **M11:** Privacy, security, diagnostics, observability, and performance hardening.

---

## 11. Out-of-Scope Items

M0-006 does not add:

- Repository implementations
- Remote data sources
- Local data sources
- DTOs
- Room entities
- DAOs
- DataStore keys
- Sync workers
- Migrations
- Offline cache logic
- Runtime data transformations

---

## 12. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file defines data-layer ownership without claiming data implementation in M0.

---

## Summary

**Data Layer Responsibilities:**
- Define repository implementation boundaries.
- Coordinate network, database, datastore, sync, and backup sources after contracts exist.
- Transform source-specific models into domain-safe models.
- Map source failures into domain-safe error contracts.
- Support offline, sync, backup, and rollback behavior in M3.
- Preserve privacy by preventing DTO, entity, token, secret, and PII leakage.
