# MyMind baseline audit

## Repository baseline
- Starting point: MyBrain fork, development branch `mymind-development`.
- Original upstream and the fork's `master` branch are not modified by this work.
- Source namespace is retained as `com.mhss.app.mybrain` for the first migration stage.
- New Android application ID: `app.mymind.productivity`.
- Debug application ID: `app.mymind.productivity.debug`.
- New app version baseline: `1.0.0`, versionCode `1`.

## Build system observed
- Android Gradle Plugin: 9.3.0
- Kotlin: 2.4.10
- Compose Multiplatform: 1.11.1
- Java compatibility: 17
- compileSdk / targetSdk: 37
- minSdk: 26
- Verification task currently configured: `testAndroidHostTest`
- Release build task currently configured: `:androidApp:assembleRelease`

## Architecture observed
- Kotlin Multiplatform with Compose UI.
- Android application module: `androidApp`.
- Shared UI module: `composeApp`.
- Feature/data modules include tasks, notes, calendar, AI, and shared database/DI.
- Room 3 / SQLite dependencies are present.
- Existing background reminder infrastructure uses Android alarm/notification components.

## Risks and follow-up checks
1. **Build not yet verified:** the workflow is configured to run tests and assemble the release APK on pushes to this branch. Record actual GitHub Actions outcome before claiming a passing baseline.
2. **Resource naming:** the manifest references the app label and MyBrain-themed resources. Audit generated Compose resources and theme references before changing branding resources.
3. **Application identity:** changing applicationId gives MyMind a separate install. It does not migrate MyBrain private data or Play Store signing identity.
4. **Namespace:** retain the original namespace temporarily; migrate package declarations only as a separately tested refactor.
5. **AI provider:** the version catalog includes Koog provider integrations and ML Kit GenAI Prompt. Inventory actual runtime usage before replacing or removing dependencies.
6. **Data preservation:** diary/bookmark entities, migrations, backup/export paths, and foreign-key relationships must be mapped before removing those features.
7. **Manifest surface:** review exported share activities, widget receivers/configuration activities, app functions, deep links, and intent payload validation.
8. **Reminder reliability:** test exact-alarm permission states, notification permission denial, reboot rescheduling, timezone changes, and Android 13+ behavior.
9. **Licensing:** preserve GPL-3.0 notices and audit code, model, voice, font, and asset licences separately.

## Current status
The application ID and CI trigger configuration are prepared in this branch. The baseline build result remains pending GitHub Actions execution.
