# Livio - Dependency Injection Documentation

**Framework:** Hilt  
**Version:** M0 Foundation  
**Status:** Dependency-injection baseline / No runtime DI modules

**Locations:**
- `app/`
- `core/network/`
- `core/data/`
- `core/database/`
- `core/datastore/`
- `core/sync/`
- `feature/*/`

---

## 1. Overview

Livio targets Hilt for runtime dependency injection in Android modules after the owning implementation milestones introduce injectable types.

### 1.1 Why Hilt?

**Benefits:**
- Compile-time dependency validation.
- Constructor injection for implementation classes.
- Android lifecycle-aware scoping.
- ViewModel integration.
- Test replacement support.
- Consistent dependency graph ownership across modules.

**Alternative Considered:**
- Koin runtime dependency injection is not the default choice because Livio prioritizes compile-time validation and Android-recommended Hilt integration.

---

## 2. Current M0 State

M0 includes:

- Hilt plugin coordinates in `gradle/libs.versions.toml`.
- Hilt convention plugin in `build-logic/`.
- Hilt convention applied to `:app` and approved feature modules.
- Module registration for app, core, and feature modules.

M0 does not add:

- Runtime Hilt modules.
- Injected constructors.
- Hilt application class.
- Hilt-enabled activity.
- Hilt ViewModels.
- Runtime bindings.
- Test replacement bindings.

---

## 3. Planned Module Structure

Runtime DI folders are not added in M0.

Planned structure:

```text
Livio/
|-- app/
|   `-- di/                     # App-level setup after application runtime exists
|-- core/
|   |-- network/
|   |   `-- di/                 # Network clients and API service bindings
|   |-- data/
|   |   `-- di/                 # Repository implementation bindings
|   |-- database/
|   |   `-- di/                 # Room database, DAO, and migration bindings
|   |-- datastore/
|   |   `-- di/                 # DataStore and settings bindings
|   `-- sync/
|       `-- di/                 # Worker and sync dependency bindings
`-- feature/
    `-- */
        `-- di/                 # Feature-specific bindings when needed
```

DI folders must be added only by the capability PR that introduces injectable runtime classes.

---

## 4. Application Setup

### 4.1 Application Class

Livio does not add an application class in M0.

**Planned Ownership:** M5 or the first runtime app integration task that requires Hilt application setup.

**Rules:**
- Add `@HiltAndroidApp` only when the app runtime class exists.
- Keep app-level initialization minimal.
- Do not initialize feature-specific services from the application class.
- Do not load secrets from committed source files.

### 4.2 Main Activity

Livio does not add `MainActivity` in M0.

**Planned Ownership:** M5 app navigation graph setup.

**Rules:**
- Add `@AndroidEntryPoint` only when `MainActivity` exists.
- Keep activity responsibilities limited to app shell setup.
- Keep feature behavior in feature modules.

---

## 5. Planned Hilt Modules

### 5.1 App Module

**Purpose:** Application-level dependencies.

**Planned Ownership:** M5 or first runtime app shell task.

**Expected Bindings:**
- Application context.
- App-level dispatching or configuration dependencies only when approved.

**Rules:**
- Do not create broad global singletons without lifecycle justification.
- Do not place secrets in app DI modules.

### 5.2 Network Module

**Purpose:** Network client and API service bindings.

**Planned Ownership:** M2.

**Expected Bindings:**
- OkHttp client.
- Retrofit instance.
- Kotlinx Serialization converter.
- DummyJSON API services.
- HTTP mock support if required by Phase 1.

**Rules:**
- Do not log tokens, secrets, or PII.
- Keep Custom Response out of Phase 1 scope.
- Use qualifiers only when multiple clients or Retrofit instances exist.

### 5.3 Repository Module

**Purpose:** Bind repository interfaces to data implementations.

**Planned Ownership:** M3.

**Expected Bindings:**
- Domain repository interfaces to repository implementations.
- Data source implementations when needed.

**Rules:**
- Prefer `@Binds` for interface-to-implementation bindings.
- Repository implementations must satisfy domain contracts.
- Do not expose data implementation types to UI modules.

### 5.4 Database Module

**Purpose:** Room database, DAO, migration, and local storage bindings.

**Planned Ownership:** M3.

**Expected Bindings:**
- Room database.
- DAOs.
- Migration dependencies.

**Rules:**
- Keep entities and DAOs inside database/data boundaries.
- Do not expose Room entities to UI.

### 5.5 DataStore Module

**Purpose:** DataStore and settings persistence bindings.

**Planned Ownership:** M3.

**Expected Bindings:**
- DataStore instance.
- Settings data source.
- Lightweight preference services.

**Rules:**
- DataStore keys must not become public feature contracts.
- Do not store secrets without an approved security design.

### 5.6 Feature Modules

**Purpose:** Feature-specific bindings.

**Planned Ownership:** M6-M10.

**Expected Bindings:**
- Feature-specific helpers.
- Feature-specific adapters.
- ViewModel dependencies when constructor injection needs feature-local services.

**Rules:**
- Keep feature DI inside the owning feature module.
- Do not bind shared app-wide dependencies from feature modules.
- Feature modules must not depend on other feature implementation modules.

---

## 6. Repository Injection

Repository injection is not implemented in M0.

**Target Pattern:**

```text
Domain repository interface
  <- Data repository implementation
  <- Data sources
  <- Network / Database / DataStore
```

**Rules:**
- Use constructor injection for repository implementations.
- Bind repository interfaces in the owning data module.
- Keep dependencies explicit.
- Avoid dependency cycles.
- Use `Provider` or `Lazy` only when there is a real lifecycle or cycle-breaking need.

---

## 7. ViewModel Injection

ViewModel injection is not implemented in M0.

**Target Pattern:**

```text
Feature screen
  -> Hilt ViewModel
  -> Use case
  -> Repository contract
```

**Rules:**
- Use `@HiltViewModel` only when ViewModel classes exist.
- ViewModels should depend on use cases or domain contracts.
- ViewModels must not depend directly on Retrofit, Room, DataStore, DTOs, or entities.
- ViewModels should expose immutable state to screens.

---

## 8. Scopes

Scope usage starts when runtime bindings exist.

**Planned Scope Guidance:**

- `SingletonComponent` for app-wide services with app lifetime.
- `ActivityRetainedComponent` for dependencies that survive configuration changes when required.
- `ViewModelComponent` for ViewModel-scoped dependencies.
- Feature-local bindings only when feature runtime behavior requires them.

**Scope Rules:**

- Do not overuse singleton scope.
- Scope only when lifecycle ownership is clear.
- Prefer unscoped constructor-injected classes when no shared lifecycle is required.
- Avoid retaining references to Activity or View objects in long-lived scopes.

---

## 9. Qualifiers

Qualifiers are not added in M0.

**Use Qualifiers When:**
- Multiple OkHttp clients exist.
- Multiple Retrofit instances exist.
- Multiple dispatchers or execution contexts exist.
- Multiple data stores or named dependencies exist.

**Rules:**
- Name qualifiers by responsibility, not implementation detail.
- Keep qualifier definitions near the owning module.
- Do not add qualifiers before multiple bindings exist.

---

## 10. Testing With Hilt

Hilt tests are not added in M0.

**Planned Test Ownership:**
- M2 adds network binding test coverage when API services exist.
- M3 adds repository and persistence binding tests.
- M6-M10 add feature ViewModel and screen tests where Hilt injection is used.
- M11 validates security-sensitive and hardening-related bindings.

**Testing Rules:**
- Replace dependencies through test modules only in test source sets.
- Keep fake implementations domain-safe.
- Do not point tests at production secrets or production endpoints.
- Keep test bindings scoped to the owning test.

---

## 11. Common Patterns

### 11.1 Interface Binding

Use `@Binds` when binding an interface to an implementation.

**Planned Use:**
- Repository interfaces to repository implementations.
- Data source interfaces to data source implementations.

### 11.2 Provided Dependencies

Use `@Provides` when constructing third-party or framework-owned objects.

**Planned Use:**
- Retrofit.
- OkHttp.
- Room database.
- DataStore.

### 11.3 Constructor Injection

Use constructor injection for project-owned injectable classes.

**Planned Use:**
- Repository implementations.
- Use cases.
- Mappers.
- Validators.
- Data sources.

---

## 12. Hilt Component Hierarchy

Livio follows Hilt component lifetimes when runtime DI is introduced.

```text
SingletonComponent
  -> ActivityRetainedComponent
  -> ViewModelComponent
  -> ActivityComponent
  -> FragmentComponent
  -> ViewComponent
```

**Rule:** Child components can access parent component bindings. Parent components cannot access child component bindings.

---

## 13. Performance Considerations

**Planned Rules:**
- Avoid expensive eager initialization.
- Use `Lazy` for expensive dependencies that may not be used.
- Use `Provider` when a fresh instance is required.
- Keep singleton objects lightweight.
- Do not use DI modules as hidden service locators.
- Keep dependency graphs understandable and reviewable.

---

## 14. Troubleshooting

### 14.1 Missing Binding

**Likely Causes:**
- Interface has no `@Binds` binding.
- Third-party type has no `@Provides` method.
- Binding is installed in the wrong component.
- Dependency belongs to a module that is not visible to the consumer.

**Resolution:**
- Add the binding in the owning module.
- Confirm component scope.
- Confirm module dependency direction.
- Confirm feature modules do not depend on other feature implementation modules.

### 14.2 Dependency Cycle

**Likely Causes:**
- Repository depends on another repository that depends back on it.
- Feature service depends on a ViewModel or UI type.
- Shared service has too many responsibilities.

**Resolution:**
- Move shared behavior into a lower-level contract.
- Split responsibilities.
- Use `Provider` or `Lazy` only when lifecycle analysis confirms it is appropriate.

### 14.3 Wrong Scope

**Likely Causes:**
- Activity or View dependency is bound into `SingletonComponent`.
- Singleton retains short-lived Android objects.
- Feature dependency is installed globally without justification.

**Resolution:**
- Move binding to the correct component.
- Remove unnecessary scope.
- Keep Android lifecycle ownership explicit.

---

## 15. Best Practices

### 15.1 Do

- Use constructor injection for project-owned classes.
- Use `@Binds` for interface bindings.
- Use `@Provides` for third-party or framework-owned types.
- Scope dependencies only when lifecycle ownership is clear.
- Keep DI modules in the owning layer.
- Keep secrets out of DI modules.
- Keep feature DI inside feature modules unless the binding is shared core behavior.

### 15.2 Do Not

- Do not add global singletons without a clear lifecycle requirement.
- Do not expose data implementation types to UI modules.
- Do not bind DTOs, Room entities, or DataStore keys into presentation contracts.
- Do not use DI modules as hidden service locators.
- Do not create circular dependencies.
- Do not add qualifiers before multiple bindings exist.

---

## 16. Planned Migration From No Runtime DI

M0 has Hilt plugin setup but no runtime DI graph.

**Migration Steps:**

1. Add domain contracts in M1.
2. Add network service contracts and M2 network bindings.
3. Add repository implementations and M3 repository bindings.
4. Add Room, DataStore, sync, and backup bindings in M3.
5. Add app navigation shell and Hilt app setup when runtime app wiring requires it.
6. Add feature ViewModel injection in M6-M10.
7. Add test replacement bindings with the owning test tasks.
8. Harden security-sensitive bindings in M11.

---

## 17. Planned Owning Milestones

- **M2:** Network clients, API services, serialization, and HTTP mock bindings.
- **M3:** Repository, database, datastore, sync, backup, and offline-related bindings.
- **M5:** App shell and navigation-related runtime setup if required.
- **M6-M10:** Feature-specific ViewModel and feature dependency bindings.
- **M11:** Security-sensitive binding review and hardening.

---

## 18. Approved Boundaries

- Bind interfaces to implementations at the owning layer boundary.
- Do not expose data implementation types to UI modules.
- Keep feature DI inside the feature module unless a shared binding belongs in core.
- Do not add global singletons without a clear lifecycle requirement.
- Do not put secrets in DI modules.
- Prefer constructor injection for injectable classes when implementation starts.
- Use qualifiers only when multiple bindings of the same type are required.

---

## 19. Out-of-Scope Items

M0-006 does not add:

- `@HiltAndroidApp`
- `@AndroidEntryPoint`
- `@HiltViewModel`
- Hilt modules
- Entry points
- Bindings
- Qualifiers
- Runtime scopes
- Injected constructors
- Test replacement bindings

---

## 20. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file defines dependency-injection ownership without claiming runtime DI implementation in M0.

---

## Summary

**Dependency Injection Setup:**
- Hilt plugin coordinates exist in the version catalog.
- Hilt convention plugin exists in build logic.
- App and feature modules apply the Hilt convention plugin where approved.
- Runtime DI modules are not added in M0.

**Key Future Components:**
- `@HiltAndroidApp` for the application class.
- `@AndroidEntryPoint` for Android entry points.
- `@HiltViewModel` for ViewModels.
- `@Inject` for constructor injection.
- `@Provides` and `@Binds` for dependency provision.
- `@InstallIn` for component ownership.

**Current M0 State:** Hilt build setup exists. Runtime DI implementation starts when network, data, app shell, and feature runtime classes exist.
