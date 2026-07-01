# Livio - Milestone Tasks

**Version:** M0 Foundation  
**Status:** Roadmap baseline / M0 documentation in progress

**Target:** Sequential solo development  
**Goal:** Deliver Livio through small, compileable, reviewable capability PRs

---

## 1. Purpose

This document defines Livio milestone planning, task order, validation gates, and release-candidate
evidence ownership.

Livio uses sequential solo-developer milestones. Each PR should be limited to one capability, mapped
to one GitHub issue, assigned to one milestone, and validated before merge.

---

## 2. Task Checklist

### Completed Foundation

| Issue Key    | Task                      | Status   |
|--------------|---------------------------|----------|
| LIVIO-M0-001 | Create repository base    | Complete |
| LIVIO-M0-002 | Add version catalog       | Complete |
| LIVIO-M0-003 | Add Android conventions   | Complete |
| LIVIO-M0-004 | Register approved modules | Complete |
| LIVIO-M0-005 | Add quality workflow      | Complete |

### In Progress

| Issue Key    | Task                      | Status      |
|--------------|---------------------------|-------------|
| LIVIO-M0-006 | Add baseline project docs | In progress |

### Planned Next

| Milestone | Task Area                         | Status  |
|-----------|-----------------------------------|---------|
| M1        | Core contracts                    | Planned |
| M2        | Network and API                   | Planned |
| M3        | Data and offline                  | Planned |
| M4        | Design system and shared UI       | Planned |
| M5        | Navigation                        | Planned |
| M6-M10    | Feature capability implementation | Planned |
| M11       | Hardening                         | Planned |
| M12       | Release candidate evidence        | Planned |

---

## 3. Current Priority - M0 Documentation Completion

### 3.1 Documentation Structure Alignment

**Current State:**

- Root README is being aligned with a GitHub-ready documentation index.
- M0 placeholder docs are being expanded into technical baseline documents.
- Documentation must remain accurate to the current foundation-only implementation state.

**Required Changes:**

- Keep docs structured like a professional Android project.
- Keep all `.md` links clickable.
- Keep wording aligned with Clean Architecture, multi-module Android, MVI, Gradle, and GitHub
  workflow terms.
- Keep M0 implementation claims limited to repository foundation, modules, quality workflow, and
  docs.

**Files to Update:**

- `README.md`
- `docs/01_PROJECT_OVERVIEW.md`
- `docs/02_ARCHITECTURE.md`
- `docs/03_DUMMYJSON_API.md`
- `docs/04_DATA_LAYER.md`
- `docs/05_DOMAIN_LAYER.md`
- `docs/06_PRESENTATION_LAYER.md`
- `docs/07_NAVIGATION.md`
- `docs/08_BUILD_CONFIGURATION.md`
- `docs/09_MILESTONE_TASKS.md`
- `docs/10_DEPENDENCY_INJECTION.md`
- `docs/GITHUB_WORKFLOW.md`

---

### 3.2 M0 Scope Verification

**Current State:**

- M0 has module boundaries and tooling.
- M0 does not have runtime contracts, API implementation, persistence, UI, navigation graph, or DI
  bindings.

**Required Checks:**

- Confirm docs do not claim runtime feature implementation.
- Confirm docs do not include legacy sample-app wording.
- Confirm docs do not include unrelated backend, payment, identity-verification, or operator workflow references.
- Confirm docs do not include release build commands before M12 ownership.
- Confirm docs keep DummyJSON as the planned sample API source.

---

### 3.3 GitHub Workflow Consistency

**Current State:**

- M0 issues and PRs use the canonical GitHub workflow.
- M0 tasks use small scoped branches and squash merges.

**Required Checks:**

- Issue keys follow `LIVIO-M<#>-<###>`.
- Branches use lowercase scoped names.
- Commit messages use Conventional Commit format with `Refs: LIVIO-M<#>-<###>`.
- PRs close GitHub issues only through PR/MR body links.
- Milestones remain explicit and scoped.

---

## 4. Milestone Execution Order

Total estimate: 24 solo work-weeks.

| Milestone | Name                        | Estimate | PR Target | Outcome                                                       |
|-----------|-----------------------------|---------:|----------:|---------------------------------------------------------------|
| M0        | Foundation                  |   1 week |       5-7 | Buildable repository foundation                               |
| M1        | Core Contracts              |  2 weeks |      8-10 | Domain, model, result, error, use case, and MVI contracts     |
| M2        | Network And API             |  2 weeks |     10-12 | DummyJSON API services, DTO boundaries, endpoint traceability |
| M3        | Data And Offline            |  3 weeks |     12-15 | Repository implementations, Room, DataStore, sync, backup     |
| M4        | Design System And Shared UI |  2 weeks |      8-10 | Material 3 theme, design tokens, shared components            |
| M5        | Navigation                  |   1 week |       5-7 | Typed routes, graph, guarded routes, deep links               |
| M6        | Identity                    |  2 weeks |      8-10 | Onboarding, auth, profile, settings foundation                |
| M7        | Shopping                    |  2 weeks |      8-10 | Products, carts, orders                                       |
| M8        | Recipes And Todos           |  2 weeks |      8-10 | Recipes and todos                                             |
| M9        | Social And Quotes           |  2 weeks |      8-10 | Posts, comments, quotes                                       |
| M10       | App Experience              |  2 weeks |      8-10 | Search, saved items, developer diagnostics, app polish        |
| M11       | Hardening                   |  2 weeks |      8-10 | Quality, accessibility, privacy, security, performance        |
| M12       | Release Candidate           |   1 week |       5-7 | QA evidence, screenshots, release artifacts, demo script      |

---

## 5. Task Execution Order

### M0 Foundation

1. Create repository base.
2. Add version catalog.
3. Add Android conventions.
4. Register approved modules.
5. Add quality workflow.
6. Add baseline project docs.
7. Validate M0 exit gate.
8. Create M0 completion tag after all M0 checks pass.

### M1-M5 Foundation Gates

1. Add core contracts before implementation.
2. Add API and endpoint traceability before repository implementation.
3. Add data and offline implementation before feature UI depends on it.
4. Add design system and shared UI before feature screens scale.
5. Add navigation contracts and graph before feature workflows depend on routes.

### M6-M10 Feature Delivery

1. Add one feature capability per PR.
2. Keep each PR compileable.
3. Add tests with implemented behavior.
4. Update docs with completed capability scope.
5. Keep feature modules independent.

### M11-M12 Finalization

1. Harden accessibility, privacy, security, diagnostics, and performance.
2. Complete QA matrix.
3. Capture screenshots and output evidence.
4. Prepare release candidate artifacts.
5. Prepare demo video script and portfolio evidence.

---

## 6. Documentation Reference Checklist

Before completing a documentation task, review:

- Root [README.md](../README.md)
- [01_PROJECT_OVERVIEW.md](./01_PROJECT_OVERVIEW.md)
- [02_ARCHITECTURE.md](./02_ARCHITECTURE.md)
- [03_DUMMYJSON_API.md](./03_DUMMYJSON_API.md)
- [04_DATA_LAYER.md](./04_DATA_LAYER.md)
- [05_DOMAIN_LAYER.md](./05_DOMAIN_LAYER.md)
- [06_PRESENTATION_LAYER.md](./06_PRESENTATION_LAYER.md)
- [07_NAVIGATION.md](./07_NAVIGATION.md)
- [08_BUILD_CONFIGURATION.md](./08_BUILD_CONFIGURATION.md)
- [10_DEPENDENCY_INJECTION.md](./10_DEPENDENCY_INJECTION.md)
- [GITHUB_WORKFLOW.md](./GITHUB_WORKFLOW.md)

---

## 7. Validation Checklist

Run in Git Bash before requesting review:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

Expected M0 result:

- Root Gradle project loads.
- Approved modules are registered.
- Quality checks run.
- No feature implementation is present.
- Documentation links resolve.

---

## 8. Milestone Readiness Checklist

### M0 Foundation Readiness

- Root Gradle project loads.
- Version catalog exists.
- Build convention plugins exist.
- Approved modules are registered.
- GitHub Actions Quality workflow exists.
- Baseline docs exist.
- No runtime feature code exists.

### M1 Readiness

- M0 is merged.
- M0 validation passes locally and in GitHub Actions.
- M0 tag is created after milestone completion.
- Core contract issue sequence is ready.

### M12 Release Candidate Readiness

- QA matrix is complete.
- Endpoint matrix is complete.
- Repository method matrix is complete.
- Architecture diagram is complete.
- Screenshots are captured.
- Release artifacts are prepared.
- Demo video script is prepared.
- Known gaps are documented.

---

## 9. Known Risks To Address

### DummyJSON User Data

**Risk:** DummyJSON user payloads can include fields that should not be shown directly in UI.

**Required Action:** Map user data into safe domain models and whitelist fields before UI use.

### HTTP Mock vs Custom Response

**Risk:** Mock behavior and custom response behavior can be confused.

**Required Action:** Keep HTTP Mock in Phase 1 scope. Keep Custom Response out of Phase 1 scope.

### Optional TOTP

**Risk:** TOTP secrets can be mishandled.

**Required Action:** Defer TOTP by default. If implemented later, send secrets through POST request
bodies and never log or deep-link secrets.

### Endpoint Traceability

**Risk:** API coverage can become incomplete or undocumented.

**Required Action:** Complete endpoint matrix in M2, including users filter, canonical auth routes,
todos by user, and supported random behavior.

### Portfolio Evidence

**Risk:** Release candidate can lack reviewable evidence.

**Required Action:** M12 must include endpoint matrix, architecture diagram, QA matrix, screenshots,
release artifacts, and demo video script.

---

## 10. Approved Boundaries

- M0 must remain foundation-only.
- M1 must introduce contracts before implementation.
- M2 must complete endpoint traceability before data implementation depends on API behavior.
- M3 must validate offline and persistence behavior before feature UI depends on it.
- M4 must establish shared UI before feature screens scale.
- M5 must establish navigation before feature workflows depend on routes.
- M6-M10 must deliver feature capabilities in small PRs.
- M11 must harden quality, security, accessibility, privacy, diagnostics, and performance.
- M12 must produce release candidate evidence.

---

## 11. Out-of-Scope Items

M0-006 does not add:

- New milestones
- Changed estimates
- Feature implementation tasks
- Runtime domain contracts
- API services
- Repository implementations
- Room entities or DAOs
- DataStore keys
- Compose screens
- ViewModels
- Navigation graph
- Hilt modules
- Release artifacts
- Production launch process
- App distribution process

---

## 12. Success Criteria

### M0 Success Criteria

- Repository foundation is buildable.
- Approved modules are registered.
- Quality workflow passes.
- Baseline docs are aligned and scoped.
- No feature implementation exists.
- M0 milestone can be closed.

### Full Roadmap Success Criteria

- Each milestone exits through validation.
- Each capability ships through a small PR.
- Architecture boundaries remain enforced.
- Feature modules remain independent.
- Release candidate evidence is complete in M12.

---

## 13. Release Candidate Package

M12 owns the release candidate package.

**Include:**

- Release candidate build artifact references
- QA matrix
- Endpoint matrix
- Repository method matrix
- Architecture diagram
- Screenshots
- Release notes
- Known limitations
- Demo video script
- GitHub milestone and tag summary

M0 does not generate release artifacts or distribution packages.

---

## 14. Next Phase After Release Candidate

Post-RC work is not M0 scope.

Potential future work:

- Production launch process
- Store listing content
- App distribution process
- Analytics review
- Crash reporting review
- Performance profiling
- Localization
- Additional API integrations
- Expanded automated test coverage
