# Livio - Presentation Layer Documentation

**Version:** M0 Foundation  
**Status:** Presentation-layer baseline / No UI implementation

**Locations:**
- `app/`
- `core/designsystem/`
- `core/ui/`
- `core/navigation/`
- `feature/*/`

---

## 1. Structure

Current M0 structure:

```text
Livio/
|-- app/                         # Application shell and app-level wiring
|-- core/
|   |-- designsystem/            # Material theme and design token boundary
|   |-- ui/                      # Shared UI component boundary
|   `-- navigation/              # Typed route and navigation contract boundary
`-- feature/
    |-- onboarding/              # Onboarding feature boundary
    |-- auth/                    # Identity and session feature boundary
    |-- home/                    # Home feature boundary
    |-- profile/                 # Profile feature boundary
    |-- settings/                # Settings feature boundary
    |-- search/                  # Search feature boundary
    |-- saved/                   # Saved items feature boundary
    |-- users/                   # Users feature boundary
    |-- products/                # Products feature boundary
    |-- carts/                   # Carts feature boundary
    |-- orders/                  # Orders feature boundary
    |-- recipes/                 # Recipes feature boundary
    |-- posts/                   # Posts feature boundary
    |-- comments/                # Comments feature boundary
    |-- todos/                   # Todos feature boundary
    |-- quotes/                  # Quotes feature boundary
    `-- developer/               # Developer diagnostics feature boundary
```

M0 tracks module boundaries and package documentation only. Runtime folders such as `navigation/`, `presentation/contract/`, `presentation/model/`, `presentation/route/`, `presentation/screen/`, `presentation/component/`, `presentation/viewmodel/`, and `di/` are added by the owning capability PR.

---

## 2. Current M0 State

M0 includes:

- `:app`
- `:core:designsystem`
- `:core:ui`
- `:core:navigation`
- `:feature:onboarding`
- `:feature:auth`
- `:feature:home`
- `:feature:profile`
- `:feature:settings`
- `:feature:search`
- `:feature:saved`
- `:feature:users`
- `:feature:products`
- `:feature:carts`
- `:feature:orders`
- `:feature:recipes`
- `:feature:posts`
- `:feature:comments`
- `:feature:todos`
- `:feature:quotes`
- `:feature:developer`
- Module build files
- Module README files
- Package documentation placeholders

M0 does not add:

- Compose screens
- Reusable UI components
- ViewModels
- UI state models
- UI event contracts
- One-time effect contracts
- Route entry points
- Navigation graph entries
- Previews
- Feature DI modules

---

## 3. Clean Multi-Module MVI Presentation Model

Livio uses Clean Architecture with a multi-module project structure and MVI presentation pattern.

**Target Data Flow:**

```text
User action
  -> UI event
  -> ViewModel
  -> Use case
  -> Repository contract
  -> Domain result
  -> State reducer
  -> UI state
  -> Compose screen
```

**Presentation Rules:**

- Screens render immutable UI state.
- Screens emit UI events.
- ViewModels translate UI events into use case calls.
- ViewModels expose state through lifecycle-aware streams.
- One-time effects are explicit and separate from persistent UI state.
- Feature modules do not depend on other feature modules.

M0 does not add MVI contracts or ViewModel classes. Shared MVI contracts are owned by M1.

---

## 4. App Module

### 4.1 `:app`

**Purpose:** Android application shell and app-level wiring.

**Planned Responsibilities:**
- Own the application entry point.
- Own app-level theme application.
- Own app-level navigation host after route contracts exist.
- Connect feature entry points without introducing feature-to-feature dependencies.

**Not Responsible For:**
- Feature screen implementation.
- Repository implementation.
- DTO or entity mapping.
- Business rules.

**Planned ownership:** M5 for navigation host, then M6-M10 for feature integration.

---

## 5. Design System and Shared UI

### 5.1 `:core:designsystem`

**Purpose:** Material theme, design tokens, typography, shapes, elevation, spacing, icons, and adaptive design rules.

**Planned Responsibilities:**
- Define Material 3 theme.
- Define design tokens.
- Define adaptive layout rules.
- Define preview and accessibility coverage patterns.

**Current M0 State:**
- Final brand colors are not defined.
- Typography, shapes, elevation, spacing, icons, and component styling are not defined.
- No runtime theme implementation exists.

**Planned ownership:** M4.

### 5.2 `:core:ui`

**Purpose:** Shared UI components used across feature modules.

**Planned Responsibilities:**
- Define reusable Compose components.
- Define loading, empty, error, and content state components.
- Define common list, card, toolbar, and action patterns after design tokens exist.

**Not Responsible For:**
- Feature-specific screen workflows.
- Business logic.
- Repository access.
- Navigation graph ownership.

**Planned ownership:** M4.

---

## 6. Feature Modules

Feature modules own user-facing capability screens and ViewModels after foundation contracts exist.

**Planned Feature Responsibilities:**
- Define feature-specific UI state.
- Define feature-specific UI events.
- Define feature route entry points after navigation contracts exist.
- Define feature screens and components.
- Define feature ViewModels.
- Delegate business behavior to use cases.

**Feature Modules:**
- `:feature:onboarding` - onboarding flow
- `:feature:auth` - identity and session flow
- `:feature:home` - home dashboard flow
- `:feature:profile` - profile flow
- `:feature:settings` - settings flow
- `:feature:search` - search flow
- `:feature:saved` - saved items flow
- `:feature:users` - users flow
- `:feature:products` - products flow
- `:feature:carts` - carts flow
- `:feature:orders` - orders flow
- `:feature:recipes` - recipes flow
- `:feature:posts` - posts flow
- `:feature:comments` - comments flow
- `:feature:todos` - todos flow
- `:feature:quotes` - quotes flow
- `:feature:developer` - developer diagnostics flow

M0 does not add feature internal folders or runtime feature code.

---

## 7. ViewModels

ViewModels are not added in M0.

**Planned ViewModel Responsibilities:**
- Own feature UI state.
- Accept UI events.
- Call use cases.
- Map domain results into presentation state.
- Emit one-time effects when needed.
- Avoid direct access to Retrofit, Room, DataStore, or DTOs.

**ViewModel Rules:**
- ViewModels should depend on use cases or domain contracts.
- ViewModels should not contain repository implementation details.
- ViewModels should not expose mutable state to screens.
- ViewModels should not own UI copy that belongs in resources.
- ViewModels should not start navigation directly from recomposition.

**Planned ownership:** M6-M10 after M1 domain contracts, M4 design foundations, and M5 navigation contracts exist.

---

## 8. Screens

Compose screens are not added in M0.

**Planned Screen Responsibilities:**
- Render UI state.
- Emit UI events.
- Display loading, empty, error, and content states.
- Use shared design system tokens and shared UI components.
- Request navigation through approved route contracts.

**Planned Screen Families:**
- Onboarding screens
- Authentication screens
- Home screen
- Profile screens
- Settings screens
- Search screens
- Saved item screens
- User list and user detail screens
- Product list and product detail screens
- Cart and order screens
- Recipe list and recipe detail screens
- Post and comment screens
- Todo screens
- Quote screens
- Developer diagnostics screens

**Screen Rules:**
- Screens must not consume DTOs.
- Screens must not consume Room entities.
- Screens must not perform blocking work.
- Screens must not call repositories directly.
- Screens must not trigger navigation from recomposition.

---

## 9. Reusable Components

Reusable UI components are not added in M0.

**Planned Component Families:**
- App bars and navigation actions
- Loading, empty, and error states
- Cards and list rows
- Form fields and input validation display
- Status badges and chips
- Dialogs and bottom sheets
- Image containers
- Pull-to-refresh and pagination components
- Accessibility-aware content wrappers

**Component Rules:**
- Components should use design tokens.
- Components should be stateless when possible.
- Components should expose explicit callbacks.
- Components should avoid business logic.
- Components should avoid feature-specific repository or use case dependencies.

---

## 10. Theme and Design Tokens

Theme implementation is not added in M0.

**Design Direction:**
- Material 3 component foundation.
- Material 3 Adaptive layout foundation.
- Design tokens owned by `:core:designsystem`.
- Shared components owned by `:core:ui`.

**Current M0 State:**
- Final brand colors are not defined in M0.
- No hardcoded strings, colors, dimensions, routes, status codes, or animation durations are allowed.
- Accessibility preview coverage is planned, not implemented.

**Planned Theme Work:**
- `LIVIO-M4-001` - Add Material theme
- `LIVIO-M4-002` - Add design tokens
- `LIVIO-M4-008` - Add adaptive previews
- `LIVIO-M4-009` - Cover accessibility previews

---

## 11. Navigation Integration

Navigation implementation is not added in M0.

**Planned Navigation Flow:**

```text
Screen event
  -> ViewModel effect or route callback
  -> Feature route contract
  -> App navigation host
  -> Destination screen
```

**Navigation Rules:**
- `:core:navigation` owns typed route contracts.
- `:app` owns the app navigation host.
- Feature modules expose route entry points through approved contracts.
- Feature modules must not navigate directly to another feature implementation.
- Deep links and guarded routes are owned by M5.

---

## 12. State Management

State implementation is not added in M0.

**Target State Types:**
- UI state
- UI event
- One-time effect
- Loading state
- Empty state
- Error state
- Content state

**State Rules:**
- UI state should be immutable.
- UI state should use domain-safe models.
- UI state should not expose DTOs or entities.
- One-time effects should not be stored as permanent state.
- Reducers should be deterministic where possible.

---

## 13. Accessibility and UX

Accessibility implementation is not added in M0.

**Planned Accessibility Requirements:**
- Content descriptions for meaningful images and icons.
- Touch targets that follow Android guidance.
- Text contrast validation.
- Dynamic text support.
- Keyboard and screen reader checks where applicable.
- Adaptive layout coverage for supported window sizes.

**Planned ownership:** M4 for component previews and M11 for hardening.

---

## 14. Testing and Preview Strategy

M0 validation is build and tooling validation only.

**Future Presentation Test Ownership:**
- Design system preview checks in M4.
- Shared UI component validation in M4.
- Navigation route tests in M5.
- Feature ViewModel tests in M6-M10.
- Feature screen workflow tests in M6-M10.
- Accessibility and UX hardening in M11.

---

## 15. Planned Owning Milestones

- **M1:** MVI contracts and domain contracts.
- **M4:** Design system, shared UI components, previews, and accessibility preview coverage.
- **M5:** Navigation contracts, app navigation graph, guarded routes, and deep links.
- **M6:** Identity and profile feature UI.
- **M7:** Shopping feature UI.
- **M8:** Recipes and todos feature UI.
- **M9:** Social and quotes feature UI.
- **M10:** App experience and developer diagnostics UI.
- **M11:** Accessibility, UX, privacy, security, diagnostics, and performance hardening.

---

## 16. Approved Boundaries

- UI must not consume DTOs.
- UI must not consume Room entities.
- Composables must not perform blocking work.
- Composables must not trigger navigation from recomposition.
- ViewModels should coordinate UI state and delegate business behavior to use cases.
- Feature modules must not depend on other feature modules.
- Shared UI components must not depend on feature modules.
- App-level navigation must not create feature-to-feature dependencies.

---

## 17. Out-of-Scope Items

M0-006 does not add:

- Compose screens
- Reusable UI components
- ViewModels
- UI state models
- UI event contracts
- One-time effect contracts
- Route entry points
- Navigation graph entries
- Previews
- Feature DI modules
- Runtime theme implementation

---

## 18. Validation Expectations

Run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Documentation review should confirm that this file defines presentation-layer ownership without claiming UI implementation in M0.

---

## Summary

**Presentation Layer Responsibilities:**
- Render Compose UI after M4-M10 implementation starts.
- Handle user interactions through UI events.
- Observe ViewModel state.
- Request navigation through approved route contracts.
- Apply Material 3 design tokens and shared UI components.
- Keep business logic in use cases and domain contracts.
- Keep DTOs, Room entities, and repository implementations out of UI.

**Current M0 State:** Presentation module boundaries are registered. UI implementation starts after design, domain, and navigation foundations exist.
