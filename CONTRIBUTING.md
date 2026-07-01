# Livio - Contributing Guide

**Version:** M0 Foundation  
**Status:** Active process

---

## 1. Purpose

This document defines contribution rules for Livio issues, branches, commits, pull requests,
validation, review, and merge behavior.

Livio uses small scoped PRs. Each PR must be buildable, reviewable, mapped to one issue, and aligned
with one milestone.

---

## 2. Development Model

- Create a scoped GitHub issue before implementation.
- Create a branch from the issue key.
- Keep the change limited to one capability or one documentation/build task.
- Keep feature implementation out of foundation-only tasks.
- Keep local validation and GitHub Actions validation aligned.
- Do not weaken quality gates to merge a PR.

Detailed process rules are documented in [docs/GITHUB_WORKFLOW.md](docs/GITHUB_WORKFLOW.md).

---

## 3. Issue Format

Use this issue title format:

```text
<type>(<scope>): <short description>
```

Allowed types:

- `docs`
- `feat`
- `fix`
- `refactor`
- `chore`
- `test`

Allowed labels:

- `documentation`
- `bug`
- `duplicate`
- `enhancement`
- `good first issue`
- `help wanted`
- `invalid`
- `question`
- `wontfix`

Rules:

- Every issue must belong to one milestone.
- Priority belongs in GitHub Project fields, not labels.
- Do not use issue number links in issue bodies unless explicitly requested.
- Do not rename approved modules, packages, folders, or files unless the issue owns that change.

---

## 4. Branch Format

Use lowercase branch names:

```text
<type>/livio-m<#>-<###>-<short-slug>
```

Example:

```text
docs/livio-m0-006-add-baseline-project-docs
```

Rules:

- Include the issue key.
- Use hyphen-separated slugs.
- Branch from the current default branch unless the issue states otherwise.

---

## 5. Commit Format

Use Conventional Commit format with a Livio issue reference footer:

```text
<type>(<scope>): <short description>

Refs: LIVIO-M<#>-<###>
```

Example:

```text
docs(repo): add baseline project docs

Refs: LIVIO-M0-006
```

Rules:

- Keep the subject short and imperative.
- Keep the scope aligned with the owning module or document area.
- Do not use `Closes #...` in commits.

---

## 6. Pull Request Requirements

Each PR must include:

- Summary.
- Related issue with `Closes #<Issue Number>`.
- Branch name.
- Commit message.
- Changes.
- Purpose.
- Validation.
- Architecture checklist.
- Security checklist.
- Screenshots or output notes.
- Reviewer notes.

Rules:

- Use issue number links only in PR bodies.
- Keep PR scope aligned with the issue.
- Attach or summarize validation output.
- Do not include unrelated refactors.
- Do not include runtime implementation in documentation-only tasks.

---

## 7. Checkbox Rule

Use checked boxes only for verified or already true statements.

```text
- [x] Build passes. Checked because the command completed successfully.
```

Use unchecked boxes for pending work.

```text
- [ ] Build passes. Reason: must be verified after implementation.
```

Rules:

- Do not keep `Reason:` on checked items.
- Do not keep `Checked because:` on unchecked items.
- Do not mark validation complete before running the command or confirming CI.

---

## 8. Validation

Run these commands in Git Bash before requesting review:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

The PR must state:

- Which commands passed locally.
- Which commands were not run and why.
- Whether GitHub Actions `Quality` passed.

Build and tooling ownership is documented in
[docs/08_BUILD_CONFIGURATION.md](docs/08_BUILD_CONFIGURATION.md).

---

## 9. Code Style

- Follow official Kotlin coding conventions.
- Use clear names for modules, packages, classes, functions, and variables.
- Add KDoc for public APIs.
- Keep functions focused and testable.
- Use resources, constants, design tokens, and typed routes instead of hardcoded values.
- Do not hardcode strings, colors, dimensions, routes, status codes, or animation durations.
- Keep DTOs, Room entities, and DataStore keys out of UI contracts.

---

## 10. Documentation Rules

- Update documentation when behavior, setup, architecture, workflow, or validation changes.
- Keep planned scope separate from implemented scope.
- Do not document runtime behavior before implementation exists.
- Keep M0 documentation foundation-only.
- Keep root README links and `docs/` links valid.

---

## 11. Security Rules

- Do not commit secrets, signing files, tokens, keystores, or service account files.
- Do not log tokens, passwords, authorization headers, session identifiers, or PII.
- Keep DummyJSON user payloads behind safe domain-model mapping before UI use.
- Report suspected vulnerabilities privately.

Security reporting and handling are documented in [SECURITY.md](SECURITY.md).

---

## 12. CODEOWNERS

Repository ownership is defined in [.github/CODEOWNERS](.github/CODEOWNERS).

Rules:

- Keep broad ownership active for the whole repository.
- Keep build, CI, security, app, core, feature, and docs paths explicitly owned.
- Do not bypass review for owned paths.
- Do not weaken CODEOWNERS routing to speed up a PR.

---

## 13. Review Rules

Review should confirm:

- Scope matches the issue.
- Validation results are present.
- Architecture boundaries are preserved.
- Security checklist is accurate.
- No unrelated implementation is included.
- No strict rule is weakened.
- Documentation is updated when required.

---

## 14. Merge Rule

Use squash and merge after review approval.

Squash title format:

```text
LIVIO-M<#>-<###> - <type>(<scope>): <short description> (#<PR Number>)
```

Squash description must include:

- Summary.
- Scope.
- Validation.
- Documentation changes, if applicable.
- `Closes #<Issue Number>`.
- `Signed-off-by: <Name> <email@example.com>`.

---

## Summary

**Contribution Responsibilities:**

- Keep issues scoped.
- Keep branches lowercase and traceable.
- Keep commits tied to Livio issue keys.
- Keep PRs small, buildable, and reviewable.
- Keep validation visible before merge.
- Keep security and architecture boundaries intact.
