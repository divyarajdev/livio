# Livio - Domain Layer Documentation

**Version:** M0 Foundation  
**Status:** Domain-layer baseline / No domain contracts

**Locations:**
- `core/common/`
- `core/model/`
- `core/mvi/`
- `core/domain/`

---

## 1. Structure

Current M0 structure:

```text
core/
|-- common/                     # Shared primitives and common contracts
|-- model/                      # Domain-safe model boundary
|-- mvi/                        # MVI contract boundary
`-- domain/                     # Repository and use case contract boundary
```

M0 tracks module boundaries and package documentation only. Runtime folders such as `repository/`, `usecase/`, `result/`, `error/`, `model/`, `validation/`, and `contract/` are added by the owning M1 capability PR.

---

## 2. Current M0 State

M0 includes:

- `:core:common`
- `:core:model`
- `:core:mvi`
- `:core:domain`
- Module build files
- Module README files
- Package documentation placeholders

M0 does not add:

- Result contracts
- Error contracts
- Repository interfaces
- Use case interfaces
- Domain models
- MVI runtime contracts
- Domain validation rules
- Unit tests for domain behavior

---

## 3. Domain Ownership

### 3.1 `:core:common`

**Purpose:** Shared non-Android primitives and common contracts.

**Planned Responsibilities:**
- Define cross-module primitives.
- Define common value contracts when they are not feature-specific.
- Keep shared contracts small and stable.

**Not Responsible For:**
- UI state.
- API DTOs.
- Room entities.
- Android framework wrappers.

### 3.2 `:core:model`

**Purpose:** Domain-safe model boundary.

**Planned Responsibilities:**
- Define immutable domain models.
- Define value objects used across repository contracts and use cases.
- Keep models independent from DTOs, entities, and UI copy.

**Not Responsible For:**
- API serialization models.
- Database schema models.
- Compose UI state models.
- Persistence annotations.

### 3.3 `:core:mvi`

**Purpose:** Shared MVI contract boundary.

**Planned Responsibilities:**
- Define base contracts for UI state, UI events, and one-time effects when needed.
- Keep presentation state patterns consistent across feature modules.
- Support unidirectional state flow.

**Not Responsible For:**
- Feature-specific screen state implementations in M0.
- ViewModels.
- Navigation graph implementation.
- Repository contracts.

### 3.4 `:core:domain`

**Purpose:** Repository and use case contract boundary.

**Planned Responsibilities:**
- Define repository interfaces.
- Define use case contracts.
- Define result and error contracts.
- Own business-facing validation rules after they are approved.

**Not Responsible For:**
- Repository implementations.
- Retrofit service definitions.
- Room entities or DAOs.
- DataStore keys.
- Compose screens or ViewModels.

---

## 4. Planned Repository Contracts

Repository interfaces are not added in M0.

Planned repository contract work starts in M1. Contracts must return domain-safe models and typed results.

**Planned Repository Families:**
- Authentication and session repository
- User repository
- Product repository
- Cart repository
- Order repository
- Recipe repository
- Todo repository
- Post repository
- Comment repository
- Quote repository
- Saved item repository
- Settings repository
- Developer diagnostics repository

**Repository Contract Rules:**
- Return domain-safe models only.
- Do not expose DTOs.
- Do not expose Room entities.
- Do not expose DataStore keys.
- Do not expose raw exceptions.
- Do not embed UI copy in domain errors.

---

## 5. Planned Use Case Layer

Use cases are not added in M0.

Use cases start in M1 after domain models, result contracts, error contracts, and repository interfaces are approved.

**Planned Use Case Responsibilities:**
- Coordinate one business action or query.
- Validate input using domain-safe rules.
- Call repository contracts, not repository implementations.
- Return typed results.
- Stay deterministic and unit-testable.

**Planned Use Case Families:**
- Session and identity use cases
- User profile use cases
- Product browsing use cases
- Cart and order use cases
- Recipe use cases
- Todo use cases
- Post and comment use cases
- Quote use cases
- Saved item use cases
- Settings use cases
- Developer diagnostics use cases

---

## 6. Business Rules

Business rules are not implemented in M0.

**Planned Business Rule Ownership:**
- M1 defines domain contracts and validation boundaries.
- M2 adds API-facing mapping boundaries.
- M3 adds repository implementation behavior.
- M6-M10 add feature-specific workflow rules through small capability PRs.

**Business Rule Guidelines:**
- Keep business rules out of Compose screens.
- Keep business rules out of DTOs and Room entities.
- Keep reusable rules in use cases or domain services.
- Keep feature-specific rules inside the owning feature capability.
- Add tests with each implemented behavior.

---

## 7. Domain Models vs Data Models

Livio separates domain models from source-specific models.

```text
Network DTO
  -> Mapper
  -> Domain model

Room entity
  -> Mapper
  -> Domain model

DataStore value
  -> Mapper
  -> Domain model or settings contract
```

**Domain Model Rules:**
- Domain models are safe for use by repository contracts, use cases, ViewModels, and UI state.
- DTOs remain inside `:core:network`.
- Room entities remain inside `:core:database`.
- DataStore keys remain inside `:core:datastore`.
- Domain models must not carry platform-specific persistence annotations.

---

## 8. MVI Contracts

Livio uses MVI for feature presentation.

**Target Flow:**

```text
User action
  -> UI event
  -> ViewModel
  -> Use case
  -> Repository contract
  -> Domain result
  -> State reducer
  -> UI state
  -> Screen
```

**MVI Contract Rules:**
- UI events describe user intent.
- UI state is immutable.
- One-time effects are explicit.
- Reducers are deterministic where possible.
- ViewModels do not expose mutable state to screens.

M0 does not add MVI runtime contracts. Shared MVI contracts are owned by M1.

---

## 9. Dependency Flow

```text
Presentation Layer
  -> Domain Layer
  -> Repository Contracts
  <- Data Layer Implementations
```

**Key Principle:** Dependency inversion.

- Presentation depends on domain contracts.
- Domain does not depend on data implementations.
- Data implementations depend on domain contracts.
- Feature modules do not depend on other feature modules.
- Domain contracts stay independent from Android framework APIs.

---

## 10. Testing Strategy

M0 validation is build and tooling validation only.

**Future Domain Test Ownership:**
- Result contract tests
- Error contract tests
- Domain model validation tests
- Repository interface contract tests
- Use case behavior tests
- MVI reducer tests
- Feature workflow tests that exercise use cases through ViewModels

M1 owns initial domain contract tests. Later milestones add implementation and feature behavior tests.

---

## 11. Benefits of Domain Layer

### 11.1 Testability

- Use cases can be tested without Android framework dependencies.
- Repository contracts can be faked in tests.
- Domain validation can be tested without UI or network setup.

### 11.2 Flexibility

- Data implementations can change without changing presentation contracts.
- Network and local persistence can evolve behind repository interfaces.
- Feature modules can stay independent and reviewable.

### 11.3 Separation of Concerns

- Domain layer defines what the app does.
- Data layer defines how data is fetched, cached, and persisted.
- Presentation layer defines how state is shown and events are collected.

---

## 12. Planned Owning Milestones

- **M1:** Core contracts, domain models, repository interfaces, use case contracts, result contracts, error contracts, and MVI contracts.
- **M2:** API service contracts, DTO boundaries, mapper contracts, and endpoint traceability.
- **M3:** Repository implementations, Room, DataStore, offline behavior, sync, backup, and rollback tests.
- **M6-M10:** Feature workflow rules and user-facing capability behavior.
- **M11:** Hardening for privacy, security, accessibility, quality, diagnostics, and performance.

---

## 13. Out-of-Scope Items

M0-006 does not add:

- Result contracts
- Error contracts
- Repository interfaces
- Use case interfaces
- Domain models
- MVI runtime contracts
- Domain validation rules
- Domain services
- Unit tests for domain behavior

---

## 14. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file defines domain-layer ownership without claiming domain implementation in M0.

---

## Summary

**Domain Layer Responsibilities:**
- Define domain-safe models.
- Define result and error contracts.
- Define repository interfaces.
- Define use case contracts.
- Define shared MVI contracts.
- Keep domain contracts independent from Android, DTOs, Room entities, DataStore keys, and UI copy.

**Current M0 State:** Domain module boundaries are registered. Domain behavior starts in M1.
