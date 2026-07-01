# Livio - Project Overview

**Version:** M0 Foundation  
**Last Updated:** 28 Jun 2026  
**Status:** Foundation / No feature implementation

---

## 1. App Purpose

**Livio** is a modular Android application planned around DummyJSON-backed daily-life workflows.

**Planned Capability Areas:**
- Identity and profile
- User directory
- Product catalog
- Cart and order workflows
- Recipe browsing
- Todo tracking
- Posts and comments
- Quote browsing
- Saved items
- Settings
- Developer diagnostics

M0 does not implement these workflows. M0 establishes the repository foundation, approved module boundaries, quality workflow, and documentation structure.

---

## 2. Target Audience

- **Primary:** Android users managing daily activities such as shopping, recipes, todos, saved items, and profile data.
- **Secondary:** Android reviewers and hiring teams evaluating modular architecture, quality gates, and delivery discipline.
- **Regions:** Global, not region-specific.
- **Platform:** Android mobile app.

---

## 3. Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** Modular Android with MVI and Clean Architecture boundaries
- **Dependency Injection:** Hilt
- **Navigation:** Jetpack Navigation Compose
- **Network:** Retrofit, OkHttp, Kotlinx Serialization, DummyJSON
- **Persistence:** Room and DataStore
- **Async:** Kotlin Coroutines and Flow
- **Image Loading:** Coil
- **Build:** Gradle wrapper `9.6.1`
- **Runtime:** JDK 17
- **CI:** GitHub Actions `Quality`

Current M0 state registers the stack and module boundaries. Runtime feature implementation starts in later milestones.

---

## 4. Project Structure

**Package root:** `io.github.divyarajdev.livio`

```text
Livio/
|-- app/                         # Android application module
|-- core/                        # Shared core modules
|-- feature/                     # Feature module boundaries
|-- build-logic/                 # Local Gradle convention plugins
|-- config/                      # Spotless and Detekt configuration
|-- docs/                        # Project documentation
|-- gradle/                      # Wrapper metadata and version catalog
`-- .github/                     # GitHub Actions and Dependabot configuration
```

Full approved module registration is documented in [02_ARCHITECTURE.md](./02_ARCHITECTURE.md) and [09_MILESTONE_TASKS.md](./09_MILESTONE_TASKS.md).

---

## 5. Related Surfaces

### 5.1 Android App

**Location:** `app/`
- User-facing Android application shell.
- Owns the final app entry point and application-level wiring.
- Feature runtime implementation starts after foundation milestones.

### 5.2 Core Modules

**Location:** `core/`
- Shared contracts, models, data boundaries, design system, navigation, and platform services.
- Feature modules must use approved core boundaries instead of depending on each other directly.

### 5.3 Feature Modules

**Location:** `feature/`
- User-facing capability boundaries for identity, users, products, carts, recipes, todos, posts, comments, quotes, and related app areas.
- Internal feature implementation folders are added only by the owning capability PR.

### 5.4 Project Documentation

**Location:** `docs/`
- Architecture, API scope, layer ownership, GitHub workflow, milestone tasks, and validation expectations.
- Portfolio evidence, screenshots, release artifacts, and demo script belong to M12.

---

## 6. Design System Status

**Design Direction:**
- Material 3 component foundation
- Material 3 Adaptive layout foundation
- Design tokens owned by `:core:designsystem`
- Shared UI components owned by `:core:ui`

**Current M0 State:**
- Final brand colors are not defined in M0.
- Typography, shape, elevation, spacing, icons, and component styling are not defined in M0.
- No hardcoded strings, colors, dimensions, routes, status codes, or animation durations are allowed.

**Planned Theme Work:**
- `LIVIO-M4-001` - Add Material theme
- `LIVIO-M4-002` - Add design tokens
- `LIVIO-M4-008` - Add adaptive previews
- `LIVIO-M4-009` - Cover accessibility previews

---

## 7. Documentation Structure

- **[README.md](../README.md)** - Repository documentation index
- **[01_PROJECT_OVERVIEW.md](./01_PROJECT_OVERVIEW.md)** - Project purpose, stack, structure, and M0 status
- **[02_ARCHITECTURE.md](./02_ARCHITECTURE.md)** - System architecture and module boundaries
- **[03_DUMMYJSON_API.md](./03_DUMMYJSON_API.md)** - Planned API scope and endpoint ownership
- **[04_DATA_LAYER.md](./04_DATA_LAYER.md)** - Data layer ownership and persistence scope
- **[05_DOMAIN_LAYER.md](./05_DOMAIN_LAYER.md)** - Domain contracts and use case ownership
- **[06_PRESENTATION_LAYER.md](./06_PRESENTATION_LAYER.md)** - UI, state, and feature presentation ownership
- **[07_NAVIGATION.md](./07_NAVIGATION.md)** - Navigation contracts and route ownership
- **[08_BUILD_CONFIGURATION.md](./08_BUILD_CONFIGURATION.md)** - Build, tooling, and validation commands
- **[09_MILESTONE_TASKS.md](./09_MILESTONE_TASKS.md)** - M0-M12 roadmap and milestone exit criteria
- **[10_DEPENDENCY_INJECTION.md](./10_DEPENDENCY_INJECTION.md)** - Hilt ownership and DI boundaries
- **[GITHUB_WORKFLOW.md](./GITHUB_WORKFLOW.md)** - Issue, branch, commit, PR/MR, and milestone rules

---

## 8. Current M0 State

**Implemented in M0:**
- Root Gradle project setup
- Gradle version catalog
- Local Gradle convention plugins
- Approved module registration
- GitHub Actions Quality workflow
- Baseline repository documentation

**Not Implemented in M0:**
- Runtime domain contracts
- API service interfaces
- Repository implementations
- DTOs
- Room entities and DAOs
- DataStore keys
- Compose screens
- ViewModels
- Navigation graph
- Hilt modules
- Release artifacts

---

## 9. Planned Owning Milestones

- **M1:** Core contracts, domain models, repository interfaces, use case contracts, and MVI contracts
- **M2:** Network contracts, API services, DTO boundaries, endpoint traceability, and route-family traceability
- **M3:** Data implementations, Room, DataStore, sync, offline behavior, and rollback validation
- **M4:** Design system and shared UI foundations
- **M5:** Navigation contracts, app navigation graph, guarded routes, and deep links
- **M6-M10:** Feature capability implementation
- **M11:** Hardening for quality, accessibility, privacy, security, and performance
- **M12:** Release candidate evidence and portfolio artifacts

---

## 10. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Expected M0 result:

- Gradle loads root project `Livio`.
- Approved modules are registered.
- Quality checks run.
- No feature UI or runtime implementation is present.
