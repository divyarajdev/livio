# Livio - Build Configuration Documentation

**Version:** M0 Foundation  
**Status:** Active build baseline

---

## 1. Purpose

This document defines the Livio M0 build, tooling, quality, and CI baseline.

It records the current Gradle and Android configuration without adding new repository behavior.
Build changes must remain scoped to the owning GitHub issue and milestone.

---

## 2. Current M0 State

| Area                  | Current Baseline              |
|-----------------------|-------------------------------|
| Gradle wrapper        | `9.6.1`                       |
| Android Gradle Plugin | `9.2.1`                       |
| Kotlin                | `2.4.0`                       |
| KSP                   | `2.3.9`                       |
| Java runtime          | JDK 17                        |
| Android compile SDK   | `37`                          |
| Android target SDK    | `37`                          |
| Android minimum SDK   | `26`                          |
| Package root          | `io.github.divyarajdev.livio` |
| CI workflow           | `Quality`                     |

Gradle JDK auto-download is disabled through `org.gradle.java.installations.auto-download=false`.
Developers must install JDK 17 locally. CI configures JDK 17 through GitHub Actions.

---

## 3. Build Structure

```text
Livio/
|-- settings.gradle.kts          # Plugin management, repositories, module includes
|-- build.gradle.kts             # Root build configuration
|-- gradle.properties            # Gradle, Android, Kotlin, and JDK behavior
|-- gradle/
|   |-- libs.versions.toml       # Version catalog
|   `-- wrapper/                 # Gradle wrapper metadata
|-- build-logic/                 # Local convention plugins
|-- config/
|   |-- spotless/                # Formatting and license header configuration
|   `-- detekt/                  # Static analysis configuration
`-- .github/
    |-- workflows/quality.yml    # CI validation workflow
    `-- dependabot.yml           # Dependency update monitoring
```

---

## 4. Version Catalog

**Owner:** `gradle/libs.versions.toml`

The version catalog owns:

- SDK values.
- Android, Kotlin, KSP, Hilt, Spotless, and Detekt plugin versions.
- AndroidX, Compose, networking, persistence, dependency injection, image loading, test, and quality
  aliases.
- Gradle plugin implementation aliases used by `build-logic/`.

**Rules:**

- Do not hardcode dependency versions in module build files.
- Do not change versions without an explicit Gradle or dependency issue.
- Do not apply plugins from the version catalog directly inside feature implementation tasks unless
  the task owns build configuration.

---

## 5. Build Logic

**Owner:** `build-logic/`

Build logic owns local Gradle convention plugins:

- Android application convention.
- Android library convention.
- Android Compose convention.
- Android Hilt convention.
- Kotlin JVM convention.
- Spotless convention.
- Detekt convention.

**Rules:**

- Keep shared Gradle behavior in convention plugins.
- Keep module build files small and consistent.
- Do not duplicate SDK, Java, Kotlin, Compose, Hilt, Spotless, or Detekt configuration across
  modules.
- Do not weaken convention plugins to make one PR pass.

---

## 6. Android Baseline

**Current Values:**

- `minSdk = 26`
- `compileSdk = 37`
- `targetSdk = 37`
- Java compatibility target: 17
- Kotlin JVM target: 17
- AndroidX enabled
- Non-transitive `R` classes enabled

`android.suppressUnsupportedCompileSdk=37.0` is present in `gradle.properties` to keep the chosen
compile SDK baseline explicit. Do not change SDK values or suppression behavior outside an approved
Gradle task.

---

## 7. Quality Tools

### 7.1 Spotless

**Owner:** `config/spotless/` and the Spotless convention plugin.

Spotless owns:

- Kotlin formatting.
- Kotlin Gradle script formatting.
- Markdown and miscellaneous text formatting where configured.
- License header application where configured.

### 7.2 Detekt

**Owner:** `config/detekt/` and the Detekt convention plugin.

Detekt owns:

- Static analysis baseline rules.
- Formatting integration.
- Kotlin quality checks.

### 7.3 Android Lint

Android lint runs through the Gradle Android modules.

**Rule:** Quality gates should not be weakened to pass a PR.

---

## 8. CI Workflow

**Owner:** `.github/workflows/quality.yml`

The `Quality` workflow runs on:

- `pull_request`
- `push` to `master`
- `workflow_dispatch`

The workflow uses:

- `ubuntu-latest`
- `actions/checkout`
- `actions/setup-java` with Temurin JDK 17
- `gradle/actions/setup-gradle`
- least-privilege `contents: read`

CI commands:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

---

## 9. Dependency Monitoring

**Owner:** `.github/dependabot.yml`

Dependabot monitors dependency updates for the configured ecosystems.

**Rules:**

- Treat dependency updates as scoped maintenance work.
- Do not combine dependency updates with feature implementation.
- Review security alerts before merging dependency changes.
- Keep dependency changes mapped to explicit issues when they affect build behavior.

---

## 10. Local Developer Requirements

Developers must have:

- Git Bash or a shell that can run the Gradle wrapper.
- JDK 17 installed locally.
- Android SDK components required for compile SDK `37`.
- Android Studio configured to use JDK 17.

Local validation should use the Gradle wrapper, not a globally installed Gradle binary.

---

## 11. Planned Owning Milestones

- **M0:** Root Gradle setup, version catalog, build-logic conventions, module registration, quality
  workflow, and baseline documentation.
- **M1-M12:** Build tooling updates only through explicitly scoped build, dependency, or quality
  tasks.
- **M11:** Late hardening of quality gates if production code requires stricter checks.
- **M12:** Release candidate validation, evidence, and artifact references.

---

## 12. Approved Boundaries

- `gradle/libs.versions.toml` owns dependency and plugin coordinates.
- `build-logic/` owns shared Gradle convention plugins.
- `config/spotless/` owns formatting and license header configuration.
- `config/detekt/` owns static analysis configuration.
- `.github/workflows/quality.yml` owns CI validation.
- `.github/dependabot.yml` owns dependency update monitoring.
- Quality gates should not be weakened to pass a PR.

---

## 13. Out-of-Scope Items

M0-006 does not add:

- New Gradle plugins.
- Dependency version changes.
- Module registration changes.
- CI workflow changes.
- Release signing.
- Publishing.
- App distribution.
- Runtime source code.

---

## 14. Troubleshooting

### 14.1 JDK 17 Not Found

**Resolution:**

1. Install JDK 17.
2. Configure Android Studio to use JDK 17.
3. Set `JAVA_HOME` if required by the local shell.
4. Restart Git Bash or Android Studio.
5. Run `./gradlew help`.

### 14.2 Unsupported Compile SDK Warning

**Resolution:**

1. Confirm `compileSdk = 37` is still the approved baseline.
2. Confirm `android.suppressUnsupportedCompileSdk=37.0` remains in `gradle.properties`.
3. Do not lower SDK values without an approved Gradle task.
4. Run `./gradlew lintDebug`.

### 14.3 Quality Workflow Failure

**Resolution:**

1. Open the first failing GitHub Actions step.
2. Reproduce the same command locally in Git Bash.
3. Fix only the owning task scope.
4. Re-run the affected command before requesting review.

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

Documentation review should confirm that this file describes the current M0 build baseline and does
not introduce new build behavior.

---

## Summary

**Build Configuration Responsibilities:**

- Define the approved Gradle wrapper baseline.
- Centralize dependency and plugin versions.
- Keep module build files consistent through convention plugins.
- Run formatting, static analysis, lint, and tests locally and in CI.
- Keep build, dependency, and quality changes scoped to explicit issues.

**Current M0 State:** Build foundation is active. Runtime feature implementation is not part of this
document.
