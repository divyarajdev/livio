# Livio - Navigation Documentation

**Version:** M0 Foundation  
**Status:** Navigation baseline / No navigation graph

**Locations:**
- `core/navigation/`
- `app/`
- `feature/*/`

---

## 1. Navigation Structure

Current M0 structure:

```text
Livio/
|-- core/
|   `-- navigation/
|-- app/
`-- feature/
    |-- onboarding/
    |-- auth/
    |-- home/
    |-- profile/
    |-- settings/
    |-- search/
    |-- saved/
    |-- users/
    |-- products/
    |-- carts/
    |-- orders/
    |-- recipes/
    |-- posts/
    |-- comments/
    |-- todos/
    |-- quotes/
    `-- developer/
```

M0 tracks module boundaries and package documentation only. Runtime folders such as `route/`, `graph/`, `destination/`, `deeplink/`, and feature `navigation/` folders are added by the owning M5 capability PR.

---

## 2. Current M0 State

M0 includes:

- `:core:navigation`
- `:app`
- All approved `:feature:*` modules
- Module build files
- Module README files
- Package documentation placeholders

M0 does not add:

- Route contracts
- Destination registration
- App navigation graph
- Bottom navigation
- Deep links
- Guarded navigation logic
- Back stack policy
- Unauthorized route handling
- Restricted-device route handling
- Update-required route handling

---

## 3. Route Contracts

Route contracts are not added in M0.

**Planned Route Ownership:**

- `:core:navigation` owns shared route contracts.
- Feature modules expose feature entry points through approved route contracts.
- `:app` owns the app navigation host and destination registration.

**Planned Route Families:**

- Onboarding routes
- Authentication routes
- Home routes
- Profile routes
- Settings routes
- Search routes
- Saved item routes
- User routes
- Product routes
- Cart routes
- Order routes
- Recipe routes
- Post routes
- Comment routes
- Todo routes
- Quote routes
- Developer diagnostics routes

**Route Rules:**

- Route contracts must not expose DTOs.
- Route contracts must not expose Room entities.
- Route arguments should use stable identifiers.
- Large object payloads should not be passed as route arguments.
- Route names should be stable and testable.
- Route contracts must be introduced before feature destination registration depends on them.

---

## 4. App Navigation Graph

The app navigation graph is not added in M0.

**Planned Responsibilities:**

- Register destination entry points.
- Connect app-level start destination logic.
- Connect guarded routes.
- Connect deep links.
- Apply bottom navigation or adaptive navigation shell after design system work exists.
- Keep feature modules independent.

**Graph Ownership:**

- `:app` owns the graph implementation.
- `:core:navigation` owns shared route contracts.
- `:feature:*` modules own feature destination entry points.

**Graph Rules:**

- App graph must not create feature-to-feature implementation dependencies.
- Destination registration should use route contracts.
- Navigation side effects must not be triggered from recomposition.
- Back stack behavior must be explicit for authentication, guarded, and tab-like flows.

---

## 5. Navigation Flows

Navigation flows are not implemented in M0.

### 5.1 Onboarding and Identity Flow

```text
Onboarding
  -> Auth
  -> Home
```

**Planned Ownership:** M5 route contracts, M6 identity implementation.

### 5.2 Home and Discovery Flow

```text
Home
  -> Search
  -> Users / Products / Recipes / Posts / Quotes
  -> Detail destination
```

**Planned Ownership:** M5 route contracts, M7-M10 feature implementation.

### 5.3 Shopping Flow

```text
Products
  -> Product detail
  -> Cart
  -> Orders
```

**Planned Ownership:** M5 route contracts, M7 shopping implementation.

### 5.4 Recipes and Todos Flow

```text
Recipes
  -> Recipe detail

Todos
  -> Todo list
  -> Todo detail or edit flow
```

**Planned Ownership:** M5 route contracts, M8 feature implementation.

### 5.5 Social and Quotes Flow

```text
Posts
  -> Post detail
  -> Comments

Quotes
  -> Quote detail or saved action
```

**Planned Ownership:** M5 route contracts, M9 feature implementation.

### 5.6 Developer Diagnostics Flow

```text
Settings or Developer entry
  -> Developer diagnostics
  -> Endpoint matrix / build info / environment state
```

**Planned Ownership:** M10 app experience and developer diagnostics.

---

## 6. Bottom and Adaptive Navigation

Bottom navigation is not added in M0.

**Planned Navigation Shell Options:**

- Bottom navigation for compact screens.
- Navigation rail for medium-width screens.
- Navigation drawer or adaptive layout for expanded screens if approved in M4/M5.

**Planned Top-Level Destinations:**

- Home
- Search
- Saved
- Profile
- Settings
- Developer diagnostics, if enabled for non-release builds or approved debug surfaces

Final top-level destinations are approved in M5 after M4 design system foundations exist.

---

## 7. Deep Linking

Deep links are not added in M0.

**Planned Deep Link Families:**

- User detail
- Product detail
- Recipe detail
- Post detail
- Todo detail
- Quote detail
- Saved item entry
- Developer diagnostics entry, if approved

**Deep Link Rules:**

- Deep links must validate route arguments.
- Deep links must respect guarded routes.
- Deep links must not expose secrets.
- TOTP secrets must not be deep-linked if optional TOTP work is implemented later.
- Unsupported links should resolve to a safe fallback destination.

---

## 8. Guarded Navigation

Guarded navigation is not added in M0.

**Planned Guards:**

- Unauthenticated route guard
- Onboarding-required guard
- Restricted-device guard
- Update-required guard
- Optional feature-flag guard
- Optional integrity-check guard

**Guard Rules:**

- Guards should run before destination content depends on protected data.
- Guards should not create loops.
- Guards should produce deterministic fallback routes.
- Guard behavior should be tested in M5 and hardened in M11.

---

## 9. Back Stack Management

Back stack policy is not added in M0.

**Planned Rules:**

- Clear authentication destinations after successful sign-in.
- Preserve state for top-level destinations where appropriate.
- Avoid duplicate top-level destinations.
- Use explicit pop behavior for completion flows.
- Avoid passing large objects through saved state.

Back stack behavior is owned by M5 and validated with navigation tests.

---

## 10. Navigation State Management

Navigation state implementation is not added in M0.

**Target State Rules:**

- Route arguments use stable identifiers.
- ViewModels load screen data from use cases using route IDs.
- Screens do not parse raw URLs.
- Navigation effects are explicit and one-time.
- Navigation events should not be emitted repeatedly during recomposition.

---

## 11. Navigation Testing

Navigation tests are not added in M0.

**Future Test Ownership:**

- Route contract tests.
- Start destination tests.
- Guarded route tests.
- Deep-link parsing tests.
- Back stack behavior tests.
- Top-level navigation shell tests.
- Feature entry point registration tests.

M5 owns initial navigation test coverage. M11 owns hardening for restricted-device, update-required, and integrity-sensitive flows.

---

## 12. Navigation Best Practices

### 12.1 Do

- Use typed route contracts.
- Use stable identifiers as route arguments.
- Keep shared route contracts in `:core:navigation`.
- Keep the app graph in `:app`.
- Keep feature destination implementation inside the owning feature module.
- Test route, guard, deep-link, and back stack behavior.

### 12.2 Do Not

- Do not hardcode route strings across modules.
- Do not pass DTOs as route arguments.
- Do not pass Room entities as route arguments.
- Do not navigate directly between feature implementation modules.
- Do not trigger navigation from recomposition.
- Do not deep-link secrets.
- Do not keep unnecessary screens in the back stack.

---

## 13. Planned Navigation Helpers

Navigation helpers are not added in M0.

**Planned Helper Families:**

- Route builders
- Route argument validators
- Guard result types
- Deep-link parsers
- Back stack policy helpers
- Navigation test utilities

Helper APIs must be introduced only when they reduce duplication or make navigation behavior safer and more testable.

---

## 14. Planned Owning Milestones

- **M5:** Route contracts, navigation graph, guarded routes, deep links, back stack policy, and navigation tests.
- **M6:** Identity and profile route integration.
- **M7:** Shopping route integration.
- **M8:** Recipes and todos route integration.
- **M9:** Social and quotes route integration.
- **M10:** App experience, adaptive navigation shell, and developer diagnostics routes.
- **M11:** Restricted-device, update-required, privacy, security, and integrity hardening.

---

## 15. Approved Boundaries

- Route contracts must not expose DTOs or Room entities.
- Feature modules must not navigate directly to another feature module through implementation dependencies.
- Shared route contracts belong in navigation-owned modules.
- Navigation side effects must not be triggered from recomposition.
- Route arguments should use stable identifiers, not large object payloads.
- App navigation must not weaken feature module independence.

---

## 16. Out-of-Scope Items

M0-006 does not add:

- Route contracts
- Destination registration
- App navigation graph
- Bottom navigation
- Adaptive navigation shell
- Deep links
- Guarded navigation logic
- Back stack policy
- Unauthorized route handling
- Restricted-device route handling
- Update-required route handling
- Navigation tests

---

## 17. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file defines navigation ownership without claiming navigation implementation in M0.

---

## Summary

**Navigation Responsibilities:**
- Define typed route contracts in M5.
- Register app navigation destinations in M5.
- Manage guarded routes in M5.
- Support deep links in M5.
- Define back stack policy in M5.
- Integrate feature destinations without feature-to-feature dependencies.
- Harden restricted-device, update-required, and integrity-sensitive navigation in M11.

**Current M0 State:** Navigation module boundaries are registered. Navigation implementation starts in M5.
