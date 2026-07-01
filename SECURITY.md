# Livio - Security Policy

**Version:** M0 Foundation  
**Status:** Active security baseline

---

## 1. Purpose

This document defines vulnerability reporting, secret handling, PII handling, dependency monitoring,
CI security, and review expectations for Livio.

M0 is foundation-only. Runtime authentication, network integration, persistence, analytics,
crash reporting, permissions, and release signing are not implemented in M0.

---

## 2. Supported Branch

Security fixes target the default branch first.

Backports are considered only when a maintained release branch exists.

---

## 3. Reporting

Report suspected security issues privately through GitHub security reporting or direct maintainer
contact.

Do not open public issues for:

- Vulnerabilities.
- Secrets.
- Access tokens.
- Signing material.
- Exploitable behavior.
- Sensitive user-data exposure.

Public GitHub issues may be used only after sensitive details are removed or after a fix is already
released.

---

## 4. Secret Handling

Do not commit:

- API keys.
- Signing keys.
- Keystores.
- Access tokens.
- Refresh tokens.
- Service account files.
- Private certificates.
- Local machine paths that expose credentials.

Rules:

- Local secrets belong in local-only configuration such as `local.properties` or environment
  variables.
- CI secrets must use GitHub Actions secrets.
- Do not print secrets in Gradle output, app logs, tests, or CI logs.
- Do not add release signing, publishing, deployment, or distribution credentials before the owning
  milestone.

---

## 5. PII And Token Handling

Livio must not log:

- Tokens.
- Passwords.
- Authorization headers.
- Session identifiers.
- Full user payloads.
- Personally identifiable user data.

DummyJSON user data must be mapped through safe domain models before UI use.

Rules:

- UI should receive only fields required by the current capability.
- Logs should use redacted identifiers or non-sensitive diagnostics.
- DTOs must not be exposed directly to presentation modules.
- Security-sensitive fields must be covered by mapper tests when implementation starts.

---

## 6. Dependency Monitoring

Dependabot monitors:

- GitHub Actions dependencies.
- Root Gradle dependencies.
- `build-logic` Gradle dependencies.

Rules:

- Triage security alerts before release candidate work.
- Keep dependency updates scoped to explicit issues.
- Do not mix dependency updates with feature implementation.
- Do not suppress security alerts without documented justification.

---

## 7. CI Security Baseline

The `Quality` workflow uses least-privilege permissions:

- `contents: read`

The workflow does not define:

- Signing credentials.
- Publishing credentials.
- Deployment credentials.
- Distribution credentials.
- Release upload credentials.

Rules:

- Do not broaden workflow permissions without an explicit CI/security issue.
- Do not add secrets to workflow logs.
- Do not weaken quality gates to pass a PR.

---

## 8. Android Security Scope

Planned Android security work is milestone-owned:

- M2 owns network contract safety and endpoint traceability.
- M3 owns persistence safety for Room, DataStore, sync, and backup.
- M6-M10 own feature-specific security checks.
- M11 owns privacy, security, integrity, diagnostics, logging, and hardening review.
- M12 owns release candidate security evidence.

M0 does not add runtime security behavior.

---

## 9. CODEOWNERS Review

Security-sensitive paths are covered by [.github/CODEOWNERS](.github/CODEOWNERS).

Security-sensitive areas include:

- `.github/`
- `build-logic/`
- `gradle/`
- `config/`
- `core/security/`
- `core/privacy/`
- `core/integrity/`
- `core/legal/`
- `SECURITY.md`

Do not bypass CODEOWNERS review for security-sensitive changes.

---

## 10. Security Checklist For PRs

Each PR should confirm:

- No secrets are added.
- No token or PII logging is added.
- No broad workflow permission is added.
- No dependency alert is ignored.
- No DTO, Room entity, or DataStore key is exposed to UI.
- No release credential, signing setup, publishing setup, or distribution setup is added outside
  approved scope.

---

## 11. Out-of-Scope Items

M0 security documentation does not add:

- Runtime authentication.
- Token storage.
- Encryption implementation.
- Release signing.
- Publishing.
- App distribution.
- Security scan workflow changes.
- Runtime network or persistence code.

---

## Summary

**Security Responsibilities:**

- Report vulnerabilities privately.
- Keep secrets out of the repository and CI logs.
- Keep PII out of logs and presentation contracts.
- Keep dependency alerts triaged.
- Keep workflow permissions minimal.
- Keep security-sensitive changes reviewable through CODEOWNERS.
