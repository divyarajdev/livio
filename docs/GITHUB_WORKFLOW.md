# Livio - GitHub Workflow

**Version:** M0 Foundation  
**Status:** Active process

---

## 1. Purpose

This document defines the GitHub workflow for Livio issues, branches, commits, pull requests,
labels, project fields, and milestones.

The workflow supports small capability PRs and keeps issue, branch, commit, review, and milestone
metadata consistent across all milestones.

---

## 2. Workflow Summary

Livio uses this sequence:

1. Create a scoped GitHub issue.
2. Assign the issue to the correct milestone.
3. Add the approved label.
4. Fill GitHub Project fields.
5. Create a lowercase branch from the issue key.
6. Implement one small capability.
7. Run local validation in Git Bash.
8. Open a pull request with the canonical template.
9. Confirm GitHub Actions `Quality` passes.
10. Squash and merge after review.
11. Update issue, PR/MR, and milestone checklist status.

---

## 3. Required Formats

### 3.1 Issue Title

```text
<type>(<scope>): <short description>
```

Example:

```text
docs(repo): add baseline project docs
```

### 3.2 PR/MR Title

```text
<type>(<scope>): <short description>
```

Example:

```text
docs(repo): add baseline project docs
```

### 3.3 Branch Name

```text
<type>/livio-m<#>-<###>-<short-slug>
```

Example:

```text
docs/livio-m0-006-add-baseline-project-docs
```

### 3.4 Commit Message

```text
<type>(<scope>): <short description>

Refs: LIVIO-M<#>-<###>
```

Example:

```text
docs(repo): add baseline project docs

Refs: LIVIO-M0-006
```

### 3.5 Squash Merge Title

```text
LIVIO-M<#>-<###> - <type>(<scope>): <short description> (#<PR Number>)
```

Example:

```text
LIVIO-M0-006 - docs(repo): add baseline project docs (#12)
```

---

## 4. Allowed Types

Use only these types:

- `docs`
- `feat`
- `fix`
- `refactor`
- `chore`
- `test`

**Type Guidance:**

- `docs` - documentation-only changes.
- `feat` - user-facing or developer-facing capability.
- `fix` - bug fix or broken behavior correction.
- `refactor` - internal restructuring without behavior change.
- `chore` - build, tooling, CI, repository maintenance.
- `test` - test-only changes.

---

## 5. Labels

Use only approved GitHub labels:

- `documentation`
- `bug`
- `duplicate`
- `enhancement`
- `good first issue`
- `help wanted`
- `invalid`
- `question`
- `wontfix`

**Rules:**

- Do not create priority labels.
- Do not replace labels with priority labels.
- Priority belongs in GitHub Project fields.
- Most planned Livio capability work uses `enhancement`.
- Documentation-only work uses `documentation`.

---

## 6. GitHub Project Fields

Use this field order:

| Field               | Value Guidance                                  |
|---------------------|-------------------------------------------------|
| Title               | GitHub issue title                              |
| Assignees           | Project owner or assigned implementer           |
| Status              | `Todo`, `In Progress`, `In Review`, or `Done`   |
| Linked Pull Request | Pull request linked after PR creation           |
| Sub-issue Request   | Use only if GitHub project workflow requires it |
| Iteration           | Current milestone or active iteration           |
| Priority            | `P1`, `P2`, or `P3`                             |
| Effort              | `0.5d`, `1d`, `2d`, or `3d`                     |
| Estimate            | Numeric estimate if the project view uses it    |
| Start Date          | Planned start date                              |
| Target Date         | Planned completion date                         |
| Milestone           | `Milestone <#> — <Milestone Name>`              |
| Labels              | Approved GitHub label                           |
| Created             | GitHub-managed                                  |
| Updated             | GitHub-managed                                  |
| Closed              | GitHub-managed                                  |
| Repository          | `divyarajdev/livio`                             |

**Status Values:**

- `Todo` - issue is created and ready.
- `In Progress` - branch exists or work has started.
- `In Review` - pull request is open.
- `Done` - pull request is merged and issue is complete.

---

## 7. Milestones

Milestone format:

```text
Milestone <#> — <Milestone Name>
```

Examples:

```text
Milestone 0 — Foundation
Milestone 1 — Core Contracts
Milestone 2 — Network And API
```

**Rules:**

- Every issue belongs to one milestone.
- Every PR/MR states the milestone.
- Milestone scope should not change to fit an oversized PR.
- Optional work should be deferred before weakening milestone rules.

---

## 8. Issue Rules

Issues must include:

- Clear problem statement.
- Specific goal.
- Included scope.
- Excluded scope.
- Architecture requirements.
- Acceptance criteria.
- Risk and mitigation.
- Implementation notes.

**Issue Body Rules:**

- Do not use issue number links in issue bodies unless explicitly requested.
- Do not use vague titles.
- Do not rename classes, packages, folders, modules, or files unless the issue explicitly owns that
  change.
- Do not change approved architecture or strict rules through an implementation issue.

---

## 9. PR/MR Rules

PR/MRs must include:

- Summary.
- Related issue with `Closes #<Issue Number>`.
- Branch name.
- Commit message.
- Changes.
- Purpose.
- Validation.
- Architecture checklist.
- Security checklist.
- Screenshots or output section.
- Reviewer notes.

**PR/MR Body Rules:**

- Use issue number links only in PR/MR bodies.
- Use `Closes #<Issue Number>` in PR/MR, not in issue body.
- Keep PR scope aligned with the issue.
- Attach or summarize validation results.
- Do not include unrelated refactors.

---

## 10. Checkbox Rules

Use checkboxes consistently.

**Checked Items:**

```markdown
- [x] Build passes. Checked because the build completed successfully.
```

**Unchecked Items:**

```markdown
- [ ] Build passes. Reason: must be verified after implementation.
```

**Rules:**

- Use `[x]` only when the statement is already true or verified.
- Use `[ ]` when implementation, review, CI, testing, or validation is still pending.
- Every unchecked item must include `Reason:`.
- Every checked item must include `Checked because:`.
- Do not keep `Reason:` on a checked item.
- Do not keep `Checked because:` on an unchecked item.

---

## 11. Validation Commands

Run in Git Bash before requesting review:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

The PR should state:

- Which commands passed locally.
- Which commands were not run and why.
- Whether GitHub Actions `Quality` passed.

---

## 12. Squash Merge Rules

Use squash merge after review approval.

**Squash Title:**

```text
LIVIO-M<#>-<###> - <type>(<scope>): <short description> (#<PR Number>)
```

**Squash Description Should Include:**

- Summary.
- Scope.
- Validation.
- Documentation changes, if applicable.
- `Closes #<Issue Number>`.
- `Signed-off-by: <Name> <email@example.com>`.

**Rules:**

- Keep the issue key in the squash title.
- Keep the PR number in the squash title.
- Keep the GitHub issue close line in the squash body.
- Keep the signoff line in the squash body.
- Do not add unrelated issue references.

---

## 13. Branch Rules

- Branch names must be lowercase.
- Branch names must include the issue key.
- Branch names must use hyphen-separated slugs.
- Branches should start from the current default branch unless the issue explicitly states
  otherwise.

Examples:

```text
chore/livio-m0-001-create-repository-base
chore/livio-m0-002-add-version-catalog
chore/livio-m0-003-add-android-conventions
chore/livio-m0-004-register-approved-modules
chore/livio-m0-005-add-quality-workflow
docs/livio-m0-006-add-baseline-project-docs
```

---

## 14. Review Rules

Review should confirm:

- Scope matches the issue.
- Validation results are present.
- Architecture boundaries are preserved.
- Security checklist is accurate.
- No unrelated implementation is included.
- No strict rule is weakened.
- Documentation is updated when behavior, setup, workflow, or architecture changes.

---

## 15. Planned Owning Milestone

- M0 owns baseline workflow documentation.
- All future milestones reuse this workflow unless a dedicated process task changes it.
- GitHub issue, PR/MR, and milestone template changes must stay compatible with approved M0 rules.

---

## 16. Approved Boundaries

- Keep branch names lowercase.
- Keep scope aligned with the owning module or document area.
- Use issue number links only in PR/MR bodies.
- Do not use issue number links in issue bodies unless explicitly requested.
- Use only approved labels.
- Do not replace labels with priority labels.
- Do not change approved structure through process documentation.

---

## 17. Out-of-Scope Items

M0-006 does not add:

- New labels.
- New issue types.
- Release automation.
- Branch protection changes.
- GitHub Project automation changes.
- CI workflow changes.

---

## 18. Validation Expectations

Before requesting review, ask the developer to run in Git Bash:

```bash
./gradlew help
./gradlew projects
./gradlew spotlessCheck --warning-mode all
./gradlew detekt --warning-mode all
./gradlew lintDebug
./gradlew test
```

The PR should state whether each command passed locally and whether the GitHub Actions `Quality`
workflow passed.

---

## Summary

**GitHub Workflow Responsibilities:**

- Keep issues scoped.
- Keep branches readable and lowercase.
- Keep commits traceable through Livio issue keys.
- Keep PR/MR bodies connected to GitHub issue numbers.
- Keep project fields separate from labels.
- Keep milestones explicit.
- Keep validation visible before merge.
